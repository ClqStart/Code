package com.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}


object  MapMappatitionMappatitonIndex {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("WD")

    val sc = new SparkContext(conf)
    val MapADD = sc.makeRDD(1 to 10)
    val MapResult = MapADD.map(_ * 2)
    //mapPartitions将数据直接传输给Excetor,减小IO传输次数，数据内存不能释放
    val mapPartitionResult = MapADD.mapPartitions(x => x.map(_ * 2))

    val value = MapADD.mapPartitionsWithIndex((num, datas) => datas.map((x) => (num, x)))
    val mapIndexResult=MapADD.mapPartitionsWithIndex{
      case (num,datas)=>{
        datas.map((_,"分区号 "+num))
      }
    }
    //返回一个序列，而不是单一元素
    val flatADD = MapADD.flatMap((1 to _))


    MapResult.collect().foreach(x=>print(x+" "))
    println()
    mapPartitionResult.collect().foreach(x=>print(x+" "))
    println()
    mapIndexResult.foreach(print)
    println()
    value.foreach(print)
    println()
    flatADD.foreach(x=>print(x+" "))

  }
}
