package com.bigdata.spark.casething

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

object TopN2 {
  def main(args: Array[String]): Unit = {

    //同月份中温度最高的2天
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("topN")

    val sc = new SparkContext(conf)

    val dataRdd: RDD[String] = sc.textFile("data/tqdata")
    //1970-8-8	32
    val splitRDD: RDD[(Int, Int, Int, Int)] = dataRdd.map {
      case line => {
        val arrs: Array[String] = line.split("\t")
        val dataArr: Array[String] = arrs(0).split("-")
        (dataArr(0).toInt, dataArr(1).toInt, dataArr(2).toInt, arrs(1).toInt)
      }
    }
    val reduced: RDD[((Int, Int, Int), Int)] = splitRDD.map(t4 => ((t4._1, t4._2, t4._3), t4._4)).reduceByKey((x: Int, y: Int) => if (y > x) y else x)
    val re: RDD[((Int, Int), Iterable[(Int, Int)])] = reduced.map(t2 => ((t2._1._1, t2._1._2), (t2._1._3, t2._2))).groupByKey()
    re.mapValues(arr=>{
      arr.toList.sorted(new Ordering[(Int, Int)] {
        override def compare(x: (Int, Int), y: (Int, Int)) = {
          y._2.compareTo(x._2)
        }
      }).take(2)
    }).foreach(println)


    while (true) {

    }


  }
}
