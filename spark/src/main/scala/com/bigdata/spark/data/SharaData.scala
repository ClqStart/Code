package com.bigdata.spark.data

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

object SharaData {
  def main(args: Array[String]): Unit = {


    val Sparkconf = new SparkConf().setMaster("local[*]").setAppName("hbase")
    val sc = new SparkContext(Sparkconf)

    val dataRDD: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)

    var sum:Int = 0;
    val accumulator: LongAccumulator = sc.longAccumulator

    dataRDD.foreach{
      case i=>{
        accumulator.add(i)
      }
    }
    println("sum= "+accumulator.value)
  }
}
