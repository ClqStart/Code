package com.bigdata.spark.casething

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

object TopN {
  def main(args: Array[String]): Unit = {


    //map,mapvalue,flatmap,flatmapValue区别
    //优化 mapvalue,flatmapValue减少一次优化
    //kv型，k没有变化，分区器没有发生变化，分区数没有变化

    //同月份中温度最高的2天
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("topN")

    val sc = new SparkContext(conf)

    val dataRdd: RDD[String] = sc.textFile("data/tqdata")
    //1970-8-8	32
    val splitRDD: RDD[(Int,Int,Int,Int)] =dataRdd.map{
      case line=>{
        val arrs: Array[String] = line.split("\t")
        val dataArr: Array[String] = arrs(0).split("-")
        (dataArr(0).toInt,dataArr(1).toInt,dataArr(2).toInt,arrs(1).toInt)
      }
    }

    val groupAdd: RDD[((Int, Int), Iterable[(Int, Int)])] = splitRDD.map(x => ((x._1, x._2), (x._3, x._4))).groupByKey()

    val res: RDD[((Int, Int), List[(Int, Int)])] = groupAdd.mapValues((arr: Iterable[(Int, Int)]) => {
      val map = new mutable.HashMap[Int, Int]()

      arr.foreach((x: (Int, Int)) => {
        if (map.getOrElse(x._1, 0) < x._2) {
          map.put(x._1, x._2)
        }
      })
      map.toList.sorted(new Ordering[(Int, Int)] {
        override def compare(x: (Int, Int), y: (Int, Int)) = {
          y._2.compareTo(x._2)
        }
      }).take(2)
    })

    res.foreach(println)

    while (true){

    }


  }
}
