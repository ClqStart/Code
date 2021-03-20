package com.flinksql

import org.apache.flink.streaming.api.scala._
import org.apache.flink.table.api.scala._
import org.apache.flink.table.api.{DataTypes, EnvironmentSettings, Table}
import org.apache.flink.table.descriptors.{Csv, Kafka, Schema}


object KafkaOutput {
  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)

    val settings: EnvironmentSettings = EnvironmentSettings.newInstance()
      .useOldPlanner()
      .inStreamingMode()
      .build()

    val tablenv: StreamTableEnvironment = StreamTableEnvironment.create(env, settings)

    //souce
    tablenv.connect(new Kafka()
        .version("0.11")
        .topic("sensor")
        .property("zookeeper.connect","node102:2181")
        .property("bootstrap.servers","node102:9092")
    )
      .withFormat(new Csv())
      .withSchema(new Schema()
        .field("id", DataTypes.STRING())
        .field("timestamp", DataTypes.BIGINT())
        .field("temperature", DataTypes.DOUBLE()))
      .createTemporaryTable("KafkainputTable")

    //sink
    tablenv.connect(new Kafka()
      .version("0.11")
      .topic("sink")
      .property("zookeeper.connect","node102:2181")
      .property("bootstrap.servers","node102:9092")
    )
      .withFormat(new Csv())
      .withSchema(new Schema()
        .field("id", DataTypes.STRING())
        .field("temperature", DataTypes.DOUBLE()))
      .createTemporaryTable("KafkaOutputTable")

    //查询转换使用table api
    val inputTable: Table = tablenv.from("KafkainputTable")
    val tableApi: Table = inputTable
      .select('id, 'temperature)
      .filter('id === "sensor_1")


    val agg: Table = inputTable
      .groupBy('id)
      .select('id, 'id.count as 'count)


     tableApi.insertInto("KafkaOutputTable")

      env.execute("kafka  pipline SQL")
  }

}
