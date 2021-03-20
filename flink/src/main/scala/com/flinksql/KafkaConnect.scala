package com.flinksql

import org.apache.flink.streaming.api.scala._
import org.apache.flink.table.api.scala._
import org.apache.flink.table.api.{DataTypes, EnvironmentSettings, Table}
import org.apache.flink.table.descriptors.{Csv, Kafka, Schema}


object KafkaConnect {
  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)

    val settings: EnvironmentSettings = EnvironmentSettings.newInstance()
      .useOldPlanner()
      .inStreamingMode()
      .build()

    val tablenv: StreamTableEnvironment = StreamTableEnvironment.create(env, settings)

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

    val KainputTable: Table = tablenv.from("KafkainputTable")

    KainputTable.toAppendStream[(String,Long,Double)].print("TableApi")

      env.execute("flink SQL")
  }

}
