package com.bigdata.spark.sql


import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset,Row, SparkSession}
object Transfrom {

  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("spark_sql")

    val sparkSession=SparkSession.builder().config(sparkConf).getOrCreate()

    import sparkSession.implicits._

    val rdd:RDD[(Int,String,Int)]=sparkSession.sparkContext.makeRDD(List((1,"zhangshan",20),(2,"lisi",30),(3,"wangwu",40)))

    val df:DataFrame =rdd.toDF("id","name","age")
    val ds: Dataset[User] =df.as[User]

    val df1:DataFrame=ds.toDF()

    val rdd1:RDD[Row] =df1.rdd

    rdd1.foreach((row: Row) =>{
      println(row.getString(1))
    })

  }
}
case class User(id:Int,name:String,age:Int)