package com.window



import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._



object WindowApi {

  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)
    //时间语义，事件驱动时间需要提取时间
//    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
//    env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime)
//    env.setStreamTimeCharacteristic(TimeCharacteristic.IngestionTime)
    val stream: DataStream[String] = env.readTextFile("in/sensor.txt")

    val dataStream: DataStream[SensorReading] = stream.map(data => {
      val dataarray = data.split(",")
      SensorReading(dataarray(0).trim, dataarray(1).trim.toLong, dataarray(2).trim.toDouble)
    })

    /**
      * 3种窗口的Api调用
      * 下面的窗口函数必须经过keyBY，数据变成KeyStream进行下面的窗口函数
      *
      * timeWindow和countWindow是对window的封装,要想改变时区的准则需要用window函数offsize进行修改时区
      * countWindow就是创建了一个全局window,evitor(移出器)，trigger(触发器)
      * window(GlobalWindows.create()).trigger(PurgingTrigger.of(CountTrigger.of(size))
      */
    dataStream
      .map(data => (data.id, data.temperature))
      .keyBy(_._1)
      //      .window(TumblingEventTimeWindows.of(Time.seconds(5)))//滚动窗口
      //      .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
      //      .window(SlidingProcessingTimeWindows.of(Time.seconds(15),Time.seconds(3))) //滑动窗口
      //      .window(SlidingEventTimeWindows.of(Time.seconds(15),Time.seconds(3)))
      //      .window(EventTimeSessionWindows.withGap(Time.seconds(10))) //会话窗口
      //      .window(ProcessingTimeSessionWindows.withGap(Time.seconds(10)))
      //
      //      .window(ProcessingTimeSessionWindows.withDynamicGap(
      //        new SessionWindowTimeGapExtractor[Time] {
      //          override def extract(element: Time): Time = {
      //            // determine and return session gap
      //            if (element.equals(Time.seconds(5))) {
      //              element
      //            } else {
      //              Time.seconds(element.getSize + 2)
      //            }
      //          }
      //        }))
      //        .timeWindow(Time.seconds(5)) //滚动窗口
      //        .timeWindow(Time.seconds(10),Time.seconds(2)) //滑动窗口
      //        .countWindow(5) //滚动窗口
      //        .countWindow(10,2) //滑动窗口
      .countWindow(5)

    env.execute("windows")

  }

}
