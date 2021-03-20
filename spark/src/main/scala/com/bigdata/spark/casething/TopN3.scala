package com.bigdata.spark.casething

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object TopN3 {
  def main(args: Array[String]): Unit = {

    //同月份中温度最高的2天
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("topN")

    val sc = new SparkContext(conf)

    val dataRdd: RDD[String] = sc.textFile("data/tqdata")
    //1970-8-8	32
    val splitRDD: RDD[((Int, Int), (Int, Int))] = dataRdd.map {
      case line => {
        val arrs: Array[String] = line.split("\t")
        val dataArr: Array[String] = arrs(0).split("-")
        ((dataArr(0).toInt, dataArr(1).toInt), (dataArr(2).toInt, arrs(1).toInt))
      }
    }
    val res: RDD[((Int, Int), Array[(Int, Int)])] = splitRDD.combineByKey(
      (v1: (Int, Int)) => {
        Array(v1, (0, 0), (0, 0))
      },
      (oldv: Array[(Int, Int)], newv: (Int, Int)) => {
        var flag = 0
        for (i <- oldv.indices) {
          if (oldv(i)._1 == newv._1) {
            if (oldv(i)._2 < newv._2) {
              flag = 1
              oldv(i) = newv
            } else {
              flag = 2
            }
          }
        }
        if (flag == 0) {
          oldv(oldv.length - 1) = newv
        }

//        oldv.sorted(new Ordering[(Int, Int)] {
//          override def compare(x: (Int, Int), y: (Int, Int)) = {
//            y._2.compareTo(x._2)
//          }
//        })
        scala.util.Sorting.quickSort(oldv)
        oldv
      },
      (v1: Array[(Int, Int)], v2: Array[(Int, Int)]) => {
        val union: Array[(Int, Int)] = v1.union(v2)
//        union.sorted(new Ordering[(Int, Int)] {
//          override def compare(x: (Int, Int), y: (Int, Int)) = {
//            y._2.compareTo(x._2)
//          }
//        })
        scala.util.Sorting.quickSort(union)
        union
      }
    )


    res.map(x=>(x._1,x._2.toList)).foreach(println)

    //    val sorted: RDD[(Int, Int, Int, Int)] = splitRDD.sortBy(t4 => (t4._1, t4._2, t4._4), false)
    //    val mapred: RDD[((Int, Int), (Int, Int))] = sorted.map(t4 => ((t4._1, t4._2, t4._3), t4._4)).reduceByKey((x, y) => if (y > x) y else x).map(t2 => ((t2._1._1, t2._1._2), (t2._1._3, t2._2)))
    //    val res: Array[((Int, Int), Iterable[(Int, Int)])] = mapred.groupByKey().take(2)
    //   res.foreach(println)



    while (true) {

    }


  }
}
