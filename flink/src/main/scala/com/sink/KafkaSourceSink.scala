package com.sink

import java.util.Properties

import com.tranfer.SensorReading
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, _}
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer011, FlinkKafkaProducer011}

object KafkaSourceSink {

  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val properties = new Properties()

    properties.setProperty("bootstrap.servers","node102:9092")
    properties.setProperty("group.id","consumer.group")
    properties.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
    properties.setProperty("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
    properties.setProperty("auto.offset.reset","latest")


    val stream: DataStream[String] = env.addSource(new FlinkKafkaConsumer011[String]("sensorsource", new SimpleStringSchema(), properties))

    val dataStream: DataStream[String] = stream.map(data => {
      val dataarray = data.split(",")
      SensorReading(dataarray(0).trim, dataarray(1).trim.toLong, dataarray(2).trim.toDouble).toString
    })


    dataStream.addSink(new FlinkKafkaProducer011[String]("node102:9092", "sensorsink", new SimpleStringSchema()))

    dataStream.print("sink")
    env.execute()

  }
}
