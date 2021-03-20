package com.UDF

import com.tranfer.SensorReading
import org.apache.flink.api.common.functions.FilterFunction
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._
object FilterUdf {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)
    val stream: DataStream[String] = env.readTextFile("in/sensor.txt")
    val dataStream: DataStream[SensorReading] = stream.map(data => {
      val dataarray = data.split(",")
      SensorReading(dataarray(0).trim, dataarray(1).trim.toLong, dataarray(2).trim.toDouble)
    })
    val res: DataStream[SensorReading] = dataStream.filter(new Myfilter())

    res.print("filter")

    env.execute()
  }
}

class Myfilter extends FilterFunction[SensorReading]{
  override def filter(value: SensorReading): Boolean = {
    value.id.startsWith("sensor_1")

  }
}