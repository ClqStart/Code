package com.flinksql

import com.tranfer.SensorReading
import org.apache.flink.streaming.api.scala._
import org.apache.flink.table.api.Table
import org.apache.flink.table.api.scala._
import org.apache.flink.table.api.scala.StreamTableEnvironment

object ExampleSql {
  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)
    val stream: DataStream[String] = env.readTextFile("in/sensor.txt")

    val dataStream: DataStream[SensorReading] = stream.map(data => {
      val dataarray = data.split(",")
      SensorReading(dataarray(0).trim, dataarray(1).trim.toLong, dataarray(2).trim.toDouble)
    })


    // 创建表的环境
     val tableEnv: StreamTableEnvironment = StreamTableEnvironment.create(env)


    //基于流的一张表 tableApi
    val datatabale: Table = tableEnv.fromDataStream(dataStream)

    //调用Api
    val resultTable: Table = datatabale.select("id,temperature").filter("id=='sensor_1'")

    resultTable.toAppendStream[(String,Double)].print("result")

    //直接调用sql实现

    tableEnv.createTemporaryView("SQLTable",dataStream)
    val sql = "select id, temperature from SQLTable where id = 'sensor_1'"
    val Sqltable: Table = tableEnv.sqlQuery(sql)

    Sqltable.toAppendStream[(String,Double)].print("sql")



    env.execute("flink SQL")
  }

}
