package com.sink

import com.tranfer.SensorReading
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.datastream.DataStreamSink
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011
object KafkaSink {

  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)
    val stream: DataStream[String] = env.readTextFile("in/sensor.txt")
    val dataStream: DataStream[String] = stream.map(data => {
      val dataarray: Array[String] = data.split(",")
      SensorReading(dataarray(0).trim, dataarray(1).trim.toLong, dataarray(2).trim.toDouble).toString
    })

    dataStream.addSink(new FlinkKafkaProducer011[String]("node102:9092", "sensor1", new SimpleStringSchema()))

    dataStream.print("sink")
    env.execute()

  }
}
