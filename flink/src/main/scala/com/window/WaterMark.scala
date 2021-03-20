package com.window

import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

case class SensorReading (id: String, timestamp: Long, temperature: Double)

object WaterMark {

  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.getConfig.setAutoWatermarkInterval(50)
    //    env.getConfig.setAutoWatermarkInterval(500)

    val stream: DataStream[String] = env.socketTextStream("node101", 7777)

    val dataStream: DataStream[SensorReading] = stream
      .map(data => {
      val dataarray = data.split(",")
      SensorReading(dataarray(0).trim, dataarray(1).trim.toLong, dataarray(2).trim.toDouble)
    })
      .assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor[SensorReading](Time.seconds(3)) {
        override def extractTimestamp(element: SensorReading): Long= element.timestamp * 1000L
      })

    //      .assignAscendingTimestamps(_.timestamp * 1000L)//升序数据提取时间戳
    //分周期生成watermark和数据块生成watermark

    val lateTag = new OutputTag[(String, Double, Long)]("late")

    val res: DataStream[(String, Double, Long)] = dataStream
      .map(data => (data.id, data.temperature, data.timestamp))
      .keyBy(_._1)
      .timeWindow(Time.seconds(15))

      .allowedLateness(Time.minutes(1))
      .sideOutputLateData(lateTag)

      .reduce((cur, newval) => (cur._1, cur._2.min(newval._2), newval._3))

    res.getSideOutput(lateTag).print("late")
    res.print("result")

    env.execute("windows")

  }

}
