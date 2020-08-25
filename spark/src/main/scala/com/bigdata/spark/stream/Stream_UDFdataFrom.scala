package com.bigdata.spark.stream

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Stream_UDFdataFrom {

  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Stream_wC")

    val streamingContext = new StreamingContext(sparkConf, Seconds(5))

    val reciveDStream: ReceiverInputDStream[String] = streamingContext.receiverStream(new MyReceiver("node101", 9999))

    val wordDStram: DStream[String] = reciveDStream.flatMap(line => line.split(" "))

    val mapDStram: DStream[(String, Int)] = wordDStram.map(((_: String), 1))

    val wordToSunDStream: DStream[(String, Int)] = mapDStram.reduceByKey(_ + _)

    wordToSunDStream.print()

    streamingContext.start()
    streamingContext.awaitTermination()
  }
}

class MyReceiver(host: String, Port: Int) extends Receiver[String](StorageLevel.MEMORY_ONLY) {

  var socket: java.net.Socket = _


  def receive(): Unit = {
    socket = new Socket(host, Port)
    val reader = new BufferedReader(new InputStreamReader(socket.getInputStream, "UTF-8"))
    var line: String = null
    while ((line=reader.readLine())!=null){
      if("END".equals(line)){
        return
      }else{
        this.store(line)
      }

    }
  }
  override def onStart(): Unit = {

    new Thread(new Runnable {

      override def run(): Unit = {
        receive()
      }
    }).start()


  }

  override def onStop(): Unit = {
    if(socket !=null){
      socket.close()
      socket = null
    }
  }
}
