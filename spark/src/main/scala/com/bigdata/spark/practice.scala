package com.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}

object practice {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local[*]").setAppName("practice")
    val sc = new SparkContext(conf)
    val rdd = sc.textFile("in/agent.log")
    val mapAdd = rdd.map(x => {
      val fileds: Array[String] = x.split(" ")
      ((fileds(1),fileds(4)), 1)
    })
    val adRDD = mapAdd.reduceByKey(_ + _)
    val prRDD = adRDD.map(x => {
      (x._1._1, (x._1._2, x._2))
    })
    val res = prRDD.groupByKey()
    val result = res.mapValues(x => {
      x.toList.sortWith((x, y) => {
        x._2 > y._2
      }).take(3)
    })
    result.foreach(println)
  }
}
