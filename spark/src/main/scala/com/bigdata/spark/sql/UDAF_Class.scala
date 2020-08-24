package com.bigdata.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, Dataset, Encoder, Encoders, Row, SparkSession, TypedColumn}
import org.apache.spark.sql.expressions.{Aggregator, MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, LongType, StructType}

//强类型
object UDAF_Class {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("spark_sql")

    val sparkSession = SparkSession.builder().config(sparkConf).getOrCreate()

    import sparkSession.implicits._

    val udaf = new MyAgeAvgClassFubction
    //应用函数转化为列
    val avgcol: TypedColumn[userBean, Double] = udaf.toColumn.name("avgAgeC")

    val dataFrame: DataFrame = sparkSession.read.json("in/use.json")

    val UserDs: Dataset[userBean] = dataFrame.as[userBean]

    UserDs.select(avgcol).show()



    sparkSession.stop()
  }

}

case class  userBean(name:String,age:BigInt)
case class avgBuffer(var sum:BigInt, var count:Int)
class MyAgeAvgClassFubction extends Aggregator[userBean,avgBuffer,Double]{
  //缓存区初始化
  override def zero: avgBuffer = {
    avgBuffer(0,0)
  }

  //数值更新 聚合操作
  override def reduce(b: avgBuffer, a: userBean): avgBuffer = {
    b.sum = b.sum+a.age
    b.count =b.count+1
    b
  }

  //缓存区合并
  override def merge(b1: avgBuffer, b2: avgBuffer): avgBuffer = {
    b1.sum = b1.sum+b2.sum
    b1.count=b1.count+b2.count
    b1
  }

  //完成计算
  override def finish(reduction: avgBuffer): Double = {
    reduction.sum.toDouble/reduction.count
  }

  override def bufferEncoder: Encoder[avgBuffer] = Encoders.product

  override def outputEncoder: Encoder[Double] = Encoders.scalaDouble
}

