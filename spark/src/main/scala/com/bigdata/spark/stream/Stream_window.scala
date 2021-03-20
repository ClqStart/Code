package com.bigdata.spark.stream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Stream_window {

  def main(args: Array[String]): Unit = {

    println("---------------scala windows-----------------------------------------")

    val ints: List[Int] = List(1, 2, 3, 4, 5, 6)

    val iterator: Iterator[List[Int]] = ints.sliding(3,3)

    for (elem <- iterator) {
      println(elem.mkString(","))
    }
    println("----------------------------spark------------------------------------------")

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Stream_windows")

    val streamingContext = new StreamingContext(sparkConf, Seconds(3))

    //数据来源于kafka
    val kafkaRDD: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(
      streamingContext,
      "node102:2181",
      "first",
      Map("first" -> 3)  //主题和分区器的关系
    )


    //窗口大小为采集周期的整数倍，滑动步长也为采集周期的整数倍
    val windowDStream: DStream[(String, String)] = kafkaRDD.window(Seconds(9), Seconds(3))

    val wordDStram: DStream[String] = windowDStream.flatMap(t => t._2.split(" "))

    val mapDStram: DStream[(String, Int)] = wordDStram.map(((_: String), 1))

    val wordToSunDStream: DStream[(String, Int)] = mapDStram.reduceByKey(_ + _)

    wordToSunDStream.print()

    streamingContext.start()
    streamingContext.awaitTermination()

  }

}
