package com.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}


object  MakeRDD {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("WD")

    val sc = new SparkContext(conf)
    println("--------------RDD3中创建方式----------------------------")
    //内存，外部储存文件，装换
    val listRDD = sc.makeRDD(List(1, 2, 3, 4, 5))
    val arrayRDD= sc.parallelize(Array(1, 2, 3, 4))

    listRDD.foreach(x=>print(x+" "))
    arrayRDD.foreach(x=>print(x+" "))
//
//    val fileADD = sc.textFile("in")
//    fileADD.saveAsTextFile("output")


  }
}
