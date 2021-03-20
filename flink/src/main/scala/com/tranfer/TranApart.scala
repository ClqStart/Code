package com.tranfer

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._


object TranApart {

  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)
    val stream: DataStream[String] = env.readTextFile("in/sensor.txt")
    val dataStream: DataStream[SensorReading] = stream.map(data => {
      val dataarray = data.split(",")
      SensorReading(dataarray(0).trim, dataarray(1).trim.toLong, dataarray(2).trim.toDouble)
    })

    val splitStream: SplitStream[SensorReading] = dataStream.split(data => {
      if (data.temperature > 30) Seq("high") else Seq("low")
    })

    val high: DataStream[SensorReading] = splitStream.select("high")
    val low: DataStream[SensorReading] = splitStream.select("low")
    val all: DataStream[SensorReading] = splitStream.select("low","high")

    high.print("high")
    low.print("low")
    all.print("all")

    env.execute("Split")

  }

}
