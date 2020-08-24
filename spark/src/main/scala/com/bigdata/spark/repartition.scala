package com.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}


object repartition {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("WD")

    val sc = new SparkContext(conf)
    val IntRdd = sc.makeRDD(1 to 16,4)

    val reRDD = IntRdd.repartition(2)
  }
  }
