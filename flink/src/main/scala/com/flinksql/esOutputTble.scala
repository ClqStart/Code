package com.flinksql

import org.apache.flink.streaming.api.scala._
import org.apache.flink.table.api.scala._
import org.apache.flink.table.api.{DataTypes, EnvironmentSettings, Table}
import org.apache.flink.table.descriptors.{Csv, Elasticsearch, FileSystem, Json, Kafka, Schema}


object esOutputTble {
  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)

    val settings: EnvironmentSettings = EnvironmentSettings.newInstance()
      .useOldPlanner()
      .inStreamingMode()
      .build()

    val tablenv: StreamTableEnvironment = StreamTableEnvironment.create(env, settings)


//    //生产源
//    tablenv.connect(new Kafka()
//      .version("0.11")
//      .topic("sensor")
//      .property("zookeeper.connect", "node102:2181")
//      .property("bootstrap.servers", "node102:9092")
//    )
//      .withFormat(new Csv())
//      .withSchema(new Schema()
//        .field("id", DataTypes.STRING())
//        .field("timestamp", DataTypes.BIGINT())
//        .field("temperature", DataTypes.DOUBLE()))
//      .createTemporaryTable("KafkainputTable")

    tablenv.connect(new FileSystem().path("in/sensor.txt"))
      .withFormat(new Csv())
      .withSchema(new Schema()
        .field("id", DataTypes.STRING())
        .field("timestamp", DataTypes.BIGINT())
        .field("temperature", DataTypes.DOUBLE()))
      .createTemporaryTable("inputTable")



    val inputTable: Table = tablenv.from("inputTable")
    val tableApi: Table = inputTable
      .select('id, 'temperature)
      .filter('id === "sensor_1")

    tableApi.toAppendStream[(String,Double)].print("api")



    // 3.2 聚合转换
    val aggTable: Table = inputTable
      .groupBy('id) // 基于id分组
      .select('id, 'id.count as 'count)

    // 4. 输出到es
    tablenv.connect(new Elasticsearch()
      .version("6")
      .host("localhost", 9200, "http")
      .index("kafkasensor")
      .documentType("temperature")
    )
      .inUpsertMode()
      .withFormat(new Json())
      .withSchema(new Schema()
        .field("id", DataTypes.STRING())
        .field("count", DataTypes.BIGINT())
      )
      .createTemporaryTable("esOutput")

    aggTable.insertInto("esOutput")


    env.execute("es sink")
  }

}
