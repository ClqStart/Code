package com.flinksql

import org.apache.flink.streaming.api.scala._
import org.apache.flink.table.api.scala._
import org.apache.flink.table.api.{DataTypes, EnvironmentSettings, Table}
import org.apache.flink.table.descriptors.{Csv, FileSystem, Schema}


object FileOutTableApi {
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

    //输出表
    tablenv.connect(new FileSystem().path("out/sensor.txt"))
      .withFormat(new Csv())
      .withSchema(new Schema()
        .field("id", DataTypes.STRING())
        .field("temperature", DataTypes.DOUBLE()))
      .createTemporaryTable("outputTable")

    //输出表
    tablenv.connect(new FileSystem().path("out/sensor1.txt"))
      .withFormat(new Csv())
      .withSchema(new Schema()
        .field("id", DataTypes.STRING())
        .field("count", DataTypes.BIGINT()))
      .createTemporaryTable("BtTable")


    //查询转换使用table api
    val inputTable: Table = tablenv.from("inputTable")
    val tableApi: Table = inputTable
      .select('id, 'temperature)
      .filter('id === "sensor_1")


    val agg: DataStream[(String, Long)] = inputTable
      .groupBy('id)
      .select('id, 'id.count as 'count)
      .toRetractStream[(String, Long)]
      .filter(x => {
        x._1.equals(true)
      })
      .map(x => (x._2._1, x._2._2))


    val outtable: Table = tablenv.fromDataStream(agg)

    //    tableApi.toAppendStream[(String,Double)].print("result")
    //    agg.toRetractStream[(String,Long)].print("agg")

    outtable.insertInto("BtTable")


    env.execute("flink SQL")
  }

}
