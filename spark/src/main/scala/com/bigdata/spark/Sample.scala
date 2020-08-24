package com.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}


object Sample {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("WD")

    val sc = new SparkContext(conf)
    val Rdd = sc.makeRDD(1 until 10, 3)

    val NoBackRDD = Rdd.sample(false, 0.4, 4)
    val backRdd = Rdd.sample(true, 4, 2)

    NoBackRDD.foreach(x=>print(x+" "))
    println()
    backRdd.foreach(x=>print(x+" "))
  }
}
