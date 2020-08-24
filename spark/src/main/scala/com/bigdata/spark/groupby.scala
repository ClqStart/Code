package com.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}


object groupby {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("WD")

    val sc = new SparkContext(conf)
    val IntRdd = sc.makeRDD(1 until 10, 3)
    val groupRDD = IntRdd.groupBy(x => x % 2)
    groupRDD.foreach(println)

    println("-----------------------------------")
    val filterRDD = IntRdd.filter(x => x % 2 == 0)
    filterRDD.foreach(x=>print(x+" "))
  }
}
