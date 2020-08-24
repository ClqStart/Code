package com.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}


object combineByKey {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("WD")

    val sc = new SparkContext(conf)
    val input = sc.parallelize(Array(("a", 88), ("b", 95), ("a", 91), ("b", 93), ("a", 95), ("b", 98)), 2)

    val comADD = input.combineByKey((_, 1), (acc: (Int, Int), v) => (acc._1 + v, acc._2 + 1), (acc1: (Int, Int), acc2: (Int, Int)) => (acc1._1 + acc2._1, acc1._2 + acc2._2))

    val Result = comADD.map {
      case (key, value) => {
        (key, value._1 / value._2.toDouble)
      }
    }
    Result.foreach(print)
    println("-------------------------------总结-----------------------------")
    val rdd = sc.parallelize(List((1, 3), (1, 2), (1, 4), (2, 3), (3, 6), (3, 8)), 3)
    val aggRDD = rdd.aggregateByKey(0)(_ + _, _ + _)
    val foldADD = rdd.foldByKey(0)(_ + _)
    val comRDD = rdd.combineByKey(x => x, (x: Int, y: Int) => (x + y), (x: Int, y: Int) => (x + y))
    aggRDD.foreach(print)
    println()
    foldADD.foreach(print)
    println()
    comRDD.foreach(print)

  }
}