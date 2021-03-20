package com.bigdata.spark.casething

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object api_sort {
  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sort")
    val sc = new SparkContext(conf)
    val filerdd: RDD[String] = sc.textFile("data/pvuvdata", 5)
    //42.62.88.214	新疆	2018-11-12	1542011088714	734986595720971991	www.baidu.com	Click

    val pair: RDD[(String, Int)] = filerdd.map(line => (line.split("\t")(5), 1))
    val reducerdd: RDD[(String, Int)] = pair.reduceByKey(_ + _)
    val sortRdd: RDD[(Int, String)] = reducerdd.map(_.swap).sortByKey(false)
    val pv: Array[(String, Int)] = sortRdd.map(_.swap).take(5)
    pv.foreach(println)
    println("------------------uv----------------")

     val rdd: RDD[(String, String)] = filerdd.map{
      case line =>{
        val list: Array[String] = line.split("\t")
        (list(0),list(5))
      }
    }
     val splitRdd: RDD[(String, Int)] = rdd.distinct().map(x=>(x._2,1))
      splitRdd.reduceByKey(_+_).sortBy(_._2,false).take(5).foreach(println)


    Thread.sleep(Integer.MAX_VALUE)
  }

}
