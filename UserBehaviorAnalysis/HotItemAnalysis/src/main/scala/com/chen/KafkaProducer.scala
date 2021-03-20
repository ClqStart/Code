package com.chen

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import scala.io.BufferedSource

object KafkaProducer {

  def main(args: Array[String]): Unit = {
        writeToKafka("kafka")
  }

  def writeToKafka(topic:String): Unit ={
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "node102:9092")
    properties.setProperty("key.serializer",
      "org.apache.kafka.common.serialization.StringSerializer")
    properties.setProperty("value.serializer",
      "org.apache.kafka.common.serialization.StringSerializer")

   val producer = new KafkaProducer[String, String](properties)

    val bufferedSource: BufferedSource = io.Source.fromFile("data/UserBehavior.csv")

    for(line <- bufferedSource.getLines()){
      val record = new ProducerRecord[String, String](topic, line)
      producer.send(record)
    }

    producer.close()
  }



}
