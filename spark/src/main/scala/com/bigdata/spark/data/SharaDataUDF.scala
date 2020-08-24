package com.bigdata.spark.data

import java.util

import org.apache.spark.rdd.RDD
import org.apache.spark.util.{AccumulatorV2, LongAccumulator}
import org.apache.spark.{SparkConf, SparkContext}


object SharaDataUDF {
  def main(args: Array[String]): Unit = {


    val Sparkconf = new SparkConf().setMaster("local[*]").setAppName("acc")
    val sc = new SparkContext(Sparkconf)

    val dataRDD: RDD[String] = sc.makeRDD(List("hadoop", "hive", "spark", "hbase","zookeerper"), 2)
    val myaccumulator = new myaccumulator
    sc.register(myaccumulator)
    dataRDD.foreach{
      case i=>{
        myaccumulator.add(i)
      }
    }
    println("sum= "+myaccumulator.value)
  }
}

class myaccumulator extends AccumulatorV2[String,util.ArrayList[String]]{

  private val list = new util.ArrayList[String]()
  override def isZero: Boolean = list.isEmpty

  override def copy(): AccumulatorV2[String, util.ArrayList[String]] ={
    new myaccumulator()
  }

  override def reset(): Unit = list.clear()

  override def add(v: String): Unit ={if(v.contains("h")){
    list.add(v)
  }}

  override def merge(other: AccumulatorV2[String, util.ArrayList[String]]): Unit = {
    list.addAll(other.value)
  }

  override def value: util.ArrayList[String] =list
}