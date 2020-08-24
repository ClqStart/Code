package com.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}


object  glom {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("WD")

    val sc = new SparkContext(conf)
    val IntRdd = sc.makeRDD(1 until 10,3)
    val glodRDD = IntRdd.glom()
    glodRDD.collect().foreach(x=>{
      println(x.mkString(","))
    })
  }
}
