package com.bigdata.spark.stream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Stream_transform {

  def main(args: Array[String]): Unit = {


    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Stream_tranform")

    val streamingContext = new StreamingContext(sparkConf, Seconds(3))

    val socketLineDStream: ReceiverInputDStream[String] = streamingContext.socketTextStream("node101", 9999)

    //TODO 代码（Driver）
    socketLineDStream.map{
      case x =>{
        //TODO 代码（Executor）
        x
      }
    }

//    //TODO 代码（Driver）
//    socketLineDStream.transform{
//      case  rdd =>{
//        //TODO 代码（Driver） 周期性执行更换
//        rdd.map=>{
//          case x=>{
//            //TODO 代码（Executor）
//            x
//          }
//        }
//      }
//    }




  }

}
