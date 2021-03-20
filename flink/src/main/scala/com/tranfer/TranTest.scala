package com.tranfer

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._

case class SensorReading(id: String, timestamp: Long,temperature: Double)
object TranTest {

  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)
    val stream: DataStream[String] = env.readTextFile("in/sensor.txt")

    val dataStream: DataStream[SensorReading] = stream.map(data => {
      val dataarray = data.split(",")
      SensorReading(dataarray(0).trim, dataarray(1).trim.toLong, dataarray(2).trim.toDouble)
    })

    val res2: DataStream[SensorReading] = dataStream.keyBy("id")
    val res1: KeyedStream[SensorReading, String] = dataStream.keyBy(_.id)
    val res: DataStream[SensorReading] = dataStream.keyBy(0)
      .sum(2)

    res.print()//.setParallelism(1)
    printf("--------------------------------------")

    val resReduce: DataStream[SensorReading] = dataStream.keyBy(0).reduce((x, y) => {
      SensorReading(x.id, x.timestamp + 1, y.temperature + 10)
    })

    resReduce.print()

    env.execute("TT")

  }

}
