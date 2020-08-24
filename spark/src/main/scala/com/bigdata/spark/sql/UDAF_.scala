package com.bigdata.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, LongType, StructType}
//弱类型
object UDAF_ {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("spark_sql")

    val sparkSession = SparkSession.builder().config(sparkConf).getOrCreate()

    import sparkSession.implicits._

    val udaf = new MyAgeAvgFubction
    sparkSession.udf.register("avgAge",udaf)

    val dataFrame: DataFrame = sparkSession.read.json("in/use.json")

    dataFrame.createOrReplaceTempView("user")
    sparkSession.sql("select avgAge(age) from user").show



    sparkSession.stop()
  }

}

class MyAgeAvgFubction extends UserDefinedAggregateFunction{
  //输入的结构
  override def inputSchema: StructType = {
    new  StructType().add("age",LongType)
  }
  //计算的结构
  override def bufferSchema: StructType = {
    new StructType().add("sum",LongType).add("count",LongType)
  }

  //返回类型
  override def dataType: DataType = DoubleType
  //稳定性
  override def deterministic: Boolean = true

  //初始化
  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0)=0L
    buffer(1)=0L
  }
  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    buffer(0) = buffer.getLong(0)+input.getLong(0)
    buffer(1) = buffer.getLong(1)+1
  }

  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    buffer1(0) = buffer1.getLong(0)+buffer2.getLong(0)
    buffer1(1) = buffer1.getLong(1)+buffer2.getLong(1)
  }
  override def evaluate(buffer: Row): Any = {
    buffer.getLong(0).toDouble/buffer.getLong(1)
  }
}
