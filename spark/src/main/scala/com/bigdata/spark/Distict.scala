package com.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}

//进行了Shuffer,数据打乱重组到其他不同的分组
object Distict {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("WD")

    val sc = new SparkContext(conf)
    val rdd = sc.makeRDD(List(1, 2, 2, 5, 5, 3, 4, 3, 7,5,7,8,9,0,32),4)

    val disRdd = rdd.distinct()
    val disRdd2 = rdd.distinct(2)
    disRdd.foreach(println)
    disRdd2.foreach(println)
//    println("*********************"+disRdd2.partitioner.size)
//    val disCoal = disRdd2.coalesce(1)
//    println("---------------"+disCoal.partitioner.size)
  }
}
