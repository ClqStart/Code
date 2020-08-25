package com.bigdata.spark.stream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Stream_dataFromKAFKA {

  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Stream_wC")

    val streamingContext = new StreamingContext(sparkConf, Seconds(5))

    //数据来源于kafka
    val kafkaRDD: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(
      streamingContext,
      "node102:2181",
      "first",
      Map("first" -> 3)
    )

    val wordDStram: DStream[String] = kafkaRDD.flatMap(t => t._2.split(" "))

    val mapDStram: DStream[(String, Int)] = wordDStram.map(((_: String), 1))

    val wordToSunDStream: DStream[(String, Int)] = mapDStram.reduceByKey(_ + _)

    wordToSunDStream.print()

    streamingContext.start()
    streamingContext.awaitTermination()
  }

}
