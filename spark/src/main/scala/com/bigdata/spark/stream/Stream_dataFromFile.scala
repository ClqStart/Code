package com.bigdata.spark.stream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Stream_dataFromFile {

  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Stream_wC")

    val streamingContext = new StreamingContext(sparkConf, Seconds(5))

    val socketLineDStream: ReceiverInputDStream[String] = streamingContext.socketTextStream("node101", 9999)

    val wordDStram: DStream[String] = socketLineDStream.flatMap(line => line.split(" "))

    val mapDStram: DStream[(String, Int)] = wordDStram.map(((_: String), 1))

    val wordToSunDStream: DStream[(String, Int)] = mapDStram.reduceByKey(_ + _)

    wordToSunDStream.print()

    streamingContext.start()
    streamingContext.awaitTermination()
  }

}
