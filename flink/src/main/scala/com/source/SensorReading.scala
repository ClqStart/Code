package com.source

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

case class SensorReadinng(id: String, timestamp: Long,temperature: Double)

object SensorReading {
  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    import org.apache.flink.streaming.api.scala._
    val stream1: DataStream[SensorReadinng] = env.fromCollection(List(
      SensorReadinng("sensor_1", 1547718121, 35.805678945234),
      SensorReadinng("sensor_6", 1547711236, 15.805678222222),
      SensorReadinng("sensor_7", 1547718117, 6.805678945233),
      SensorReadinng("sensor_2", 1547718182, 4.805678945233224),
      SensorReadinng("sensor_10", 1547718110, 20.80567894510000)
    ))
    stream1.print("stream")
    env.fromElements(1,2,3,4,4,8,Int).print()

    env.execute("source tast")

  }
}
