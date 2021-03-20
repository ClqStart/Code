package com.window

import org.apache.flink.api.common.functions.ReduceFunction
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.assigners.{EventTimeSessionWindows, ProcessingTimeSessionWindows, SessionWindowTimeGapExtractor, SlidingEventTimeWindows, SlidingProcessingTimeWindows, TumblingEventTimeWindows, TumblingProcessingTimeWindows}
import org.apache.flink.streaming.api.windowing.time.Time


object windowTest {

  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)
//    val stream: DataStream[String] = env.readTextFile("in/sensor.txt")

    val stream: DataStream[String] = env.socketTextStream("node101", 7777)
    val dataStream: DataStream[SensorReading] = stream.map(data => {
      val dataarray = data.split(",")
      SensorReading(dataarray(0).trim, dataarray(1).trim.toLong, dataarray(2).trim.toDouble)
    })
    //每15s统计一次，窗口函数中的最小值,以及当前时间，近视对15s内的数据进行操作
    val res: DataStream[(String, Double, Long)] = dataStream
      .map(data => (data.id, data.temperature, data.timestamp))
      .keyBy(_._1)
      .timeWindow(Time.seconds(15))
      //      .minBy("temperature").minBy(1)
      .reduce((cur, newval) => (cur._1, cur._2.min(newval._2), newval._3))

    res.print("windows ")
    env.execute("windows")

  }

}
//自定义函数
class  MyReduce extends ReduceFunction[SensorReading]{
  override def reduce(value1: SensorReading, value2: SensorReading): SensorReading = {
    SensorReading(value1.id,value2.timestamp,value1.temperature.min(value2.temperature))
  }
}
