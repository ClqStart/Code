package com.bigdata.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object Transfrom1 {

  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("spark_sql")

    val sparkSession = SparkSession.builder().config(sparkConf).getOrCreate()

    import sparkSession.implicits._

    val rdd: RDD[(Int, String, Int)] = sparkSession.sparkContext.makeRDD(List((1, "zhangshan", 20), (2, "lisi", 30), (3, "wangwu", 40)))


    val UserRDD: RDD[User] =rdd.map {
      case (id, name, age) => {
        User(id, name, age)
      }
    }
    val userDS: Dataset[User] = UserRDD.toDS()
    val rdd1: RDD[User] = userDS.rdd
    rdd1.foreach(println)

    sparkSession.stop()

  }
}
