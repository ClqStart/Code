package com.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCountcount {
    def main(args: Array[String]): Unit = {

      val conf=new SparkConf()
      conf.setAppName("wordcount")
      conf.setMaster("local[*]")

      val sc = new SparkContext(conf)

      val fileRDD:RDD[String] = sc.textFile("data/testdata.txt")

      val words = fileRDD.flatMap((x: String) => {
        x.split(" ")
      })
      //    val wordList = words.map((_, 1))
      val wordList = words.map((x:String)=>{Tuple2(x,1)})

      val res:RDD[(String,Int)] = wordList.reduceByKey((x: Int, y: Int) => (x + y))

      val reserve = res.map((x) => (x._2, 1))
      val count = reserve.reduceByKey((_ + _))

      res.foreach(println)
      count.foreach(println)

      Thread.sleep(Integer.MAX_VALUE)
    }


}
