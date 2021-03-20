package com.source

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._



object SensorReadFile {
  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    val stream: DataStream[String] = env.readTextFile("in/sensor.txt")

    stream.print("file").setParallelism(1)
    env.execute("file source")
  }
}
