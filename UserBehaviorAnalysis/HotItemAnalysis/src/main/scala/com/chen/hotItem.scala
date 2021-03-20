package com.chen

import java.sql.Timestamp

import org.apache.flink.api.common.functions.AggregateFunction
import org.apache.flink.api.common.state.{ListState, ListStateDescriptor}
import org.apache.flink.api.java.tuple.Tuple1
import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

import scala.collection.mutable
import scala.collection.mutable.ListBuffer


////定义输入样例
//case class UserBehavior(UserId: Long, itemId: Long, categoryId: Int, behavior: String, timestap: Long)
//
////定义窗口聚合结果样例
//case class ItemViewCount(itemId: Long, windEnd: Long, count: Long)

object hotItem {

  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

    val inputStream: DataStream[String] = env.readTextFile("data/UserBehavior.csv")

    val dataStream: DataStream[UserBehavior] = inputStream
      .map(data => {
        val arr = data.split(",")
        UserBehavior(arr(0).toLong, arr(1).toLong, arr(2).toInt, arr(3), arr(4).toLong)
      })
      .assignAscendingTimestamps(_.timestamp * 1000L)

    //获取窗口聚合结果

    val aggStream: DataStream[ItemViewCount] = dataStream
      .filter(_.behavior == "pv")
      .keyBy("itemId")
      .timeWindow(Time.hours(1), Time.minutes(5)) //进行滑动窗口设置
      .aggregate(new CountAgg(), new ItemViewWindowResult())

    val resultStream: DataStream[String] = aggStream
      .keyBy("windEnd") //按照窗口定义topn的热门
      .process(new TopNItems(5))

//    dataStream.print("data")
//    aggStream.print("agg")
    resultStream.print()

    env.execute("hotItem")
  }

  class CountAgg extends AggregateFunction[UserBehavior, Long, Long] {
    override def createAccumulator(): Long = 0L

    override def add(in: UserBehavior, acc: Long): Long = acc + 1

    override def getResult(acc: Long): Long = acc

    override def merge(acc: Long, acc1: Long): Long = acc + acc1
  }

  class ItemViewWindowResult extends WindowFunction[Long, ItemViewCount, Tuple, TimeWindow] {

    override def apply(key: Tuple, window: TimeWindow, input: Iterable[Long], out: Collector[ItemViewCount]): Unit = {

      val itemId = key.asInstanceOf[Tuple1[Long]].f0
      val windowEnd=window.getEnd
      val count=input.iterator.next()
      out.collect(ItemViewCount(itemId,windowEnd,count))
    }
  }

 class  TopNItems(topSize:Int) extends KeyedProcessFunction[Tuple,ItemViewCount,String]{

   private var itemViewCountListState: ListState[ItemViewCount] = _

   override def open(parameters: Configuration): Unit = {
     itemViewCountListState = getRuntimeContext.getListState(new ListStateDescriptor[ItemViewCount]("itemViewCount",classOf[ItemViewCount]))

   }

   override def processElement(value: ItemViewCount, ctx: KeyedProcessFunction[Tuple, ItemViewCount, String]#Context, collector: Collector[String]): Unit = {
     // 每来一条数据，直接加入ListState
     itemViewCountListState.add(value)
     // 注册一个windowEnd + 1之后触发的定时器,注册器为时间戳为准
     ctx.timerService().registerEventTimeTimer(value.windEnd + 1)
   }
   // 当定时器触发，可以认为所有窗口统计结果都已到齐，可以排序输出了
   override def onTimer(timestamp: Long, ctx: KeyedProcessFunction[Tuple, ItemViewCount, String]#OnTimerContext, out: Collector[String]): Unit = {
     val allItemViewCounts: ListBuffer[ItemViewCount] = ListBuffer()
     val iter = itemViewCountListState.get().iterator()

     while (iter.hasNext){
       allItemViewCounts += iter.next()
     }
     // 清空状态
     itemViewCountListState.clear()

     // 按照count大小排序，取前n个
     val sortedItemViewCounts: mutable.Seq[ItemViewCount] = allItemViewCounts.sortBy(_.count)(Ordering.Long.reverse).take(topSize)

     // 将排名信息格式化成String，便于打印输出可视化展示
     val result: StringBuilder = new StringBuilder
     result.append("窗口结束时间：").append( new Timestamp(timestamp - 1) ).append("\n")

     // 遍历结果列表中的每个ItemViewCount，输出到一行
     for( i <- sortedItemViewCounts.indices ){
       val currentItemViewCount: ItemViewCount = sortedItemViewCounts(i)
       result.append("NO").append(i + 1).append(": \t")
         .append("商品ID = ").append(currentItemViewCount.itemId).append("\t")
         .append("热门度 = ").append(currentItemViewCount.count).append("\n")
     }

     result.append("\n==================================\n\n")

     Thread.sleep(10000)
     out.collect(result.toString())


   }
 }
}
