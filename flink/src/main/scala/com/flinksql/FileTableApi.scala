package com.flinksql

import org.apache.flink.streaming.api.scala._
import org.apache.flink.table.api.scala._
import org.apache.flink.table.api.{DataTypes, EnvironmentSettings, Table}
import org.apache.flink.table.descriptors.{Csv, FileSystem, Schema}


object FileTableApi {
  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)

    val settings: EnvironmentSettings = EnvironmentSettings.newInstance()
      .useOldPlanner()
      .inStreamingMode()
      .build()

    val tablenv: StreamTableEnvironment = StreamTableEnvironment.create(env, settings)

    tablenv.connect(new FileSystem().path("in/sensor.txt"))
      .withFormat(new Csv())
      .withSchema(new Schema()
        .field("id", DataTypes.STRING())
        .field("timestamp", DataTypes.BIGINT())
        .field("temperature", DataTypes.DOUBLE()))
      .createTemporaryTable("inputTable")


    //查询转换使用table api
    val inputTable: Table = tablenv.from("inputTable")
    val tableApi: Table = inputTable
      .select('id, 'temperature)
      .filter('id === "sensor_1")

    //Sql使用
    val tableSql: Table = tablenv.sqlQuery(
      """
        |select id, temperature
        |from inputTable
        |where id = 'sensor_1'
        |""".stripMargin)


    tableSql.toAppendStream[(String,  Double)].print("sql")
    tableApi.toAppendStream[(String,  Double)].print("api")


    env.execute("flink SQL")
  }

}
