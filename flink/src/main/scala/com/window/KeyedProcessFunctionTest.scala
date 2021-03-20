package com.window

import org.apache.flink.api.common.state.ValueStateDescriptor
import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.util.Collector


object KeyedProcessFunctionTest {

  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)


    val stream: DataStream[String] = env.socketTextStream("node101", 7777)
    val dataStream: DataStream[SensorReading] = stream.map(data => {
      val dataarray = data.split(",")
      SensorReading(dataarray(0).trim, dataarray(1).trim.toLong, dataarray(2).trim.toDouble)
    })
      //设置watermark
      .assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor[SensorReading](Time.seconds(1)) {
        override def extractTimestamp(element: SensorReading): Long= element.timestamp * 1000
      })


    //每15s统计一次，窗口函数中的最小值,以及当前时间，对15s内的数据进行操作
    val res: DataStream[String] = dataStream
      .keyBy(_.id)
      .process(new TempIncreAlert(10.0))

    dataStream.print("data")
    res.print("warning")

    env.execute()
  }

}


class TempIncreAlert(tag:Double) extends KeyedProcessFunction[String,SensorReading,String]{

  lazy val lastTemp = getRuntimeContext.getState(new ValueStateDescriptor[Double]("lastTemp",classOf[Double]))
  lazy val curTimers = getRuntimeContext.getState(new ValueStateDescriptor[Long]("curTimers",classOf[Long]))
  lazy  val flag = getRuntimeContext.getState(new ValueStateDescriptor[Boolean]("flag",classOf[Boolean]))
  override def processElement(value: SensorReading, ctx: KeyedProcessFunction[String, SensorReading, String]#Context, out: Collector[String]): Unit = {

    //获取上一个时间的温度，并且更新
    val preTemp: Double = lastTemp.value()
    lastTemp.update(value.temperature)
    val curValue = curTimers.value()

    if( flag.value() && (value.temperature>preTemp) && curValue==0.0){
      val curtime =ctx.timerService().currentProcessingTime() + (tag.toLong * 1000)
      ctx.timerService().registerProcessingTimeTimer(curtime)
      curTimers.update(curtime)
    }else if ( flag.value() && (preTemp > value.temperature || preTemp ==0.0)){
      ctx.timerService().deleteProcessingTimeTimer(curValue)
      curTimers.clear()
    }else{
      flag.update(true)
    }
  }
  override def onTimer(timestamp: Long, ctx: KeyedProcessFunction[String, SensorReading, String]#OnTimerContext, out: Collector[String]): Unit = {
    //输出注册回调报警信息

    out.collect("传感器"+ctx.getCurrentKey+"的温度"+tag+"秒连续上升")
    curTimers.clear()
  }
}


class MyProcess() extends KeyedProcessFunction[String,SensorReading,String]{

  override def processElement(value: SensorReading, ctx: KeyedProcessFunction[String, SensorReading, String]#Context, out: Collector[String]): Unit = {

    ctx.timerService().registerEventTimeTimer(2000)

  }
}
