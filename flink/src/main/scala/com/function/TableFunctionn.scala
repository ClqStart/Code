package com.function

import com.tranfer.SensorReading
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.table.api.scala._
import org.apache.flink.table.api.{EnvironmentSettings, Table, Tumble}
import org.apache.flink.table.functions.{ScalarFunction, TableFunction}
import org.apache.flink.types.Row

object TableFunctionn {
  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

    val settings: EnvironmentSettings = EnvironmentSettings
      .newInstance()
      .useBlinkPlanner()
      .inStreamingMode()
      .build()

    val tableEnv: StreamTableEnvironment = StreamTableEnvironment.create(env, settings)

    val InputStream: DataStream[String] = env.readTextFile("in/sensor.txt")

    val dataStream: DataStream[SensorReading] = InputStream.map(data => {
      val dataarray = data.split(",")
      SensorReading(dataarray(0).trim, dataarray(1).trim.toLong, dataarray(2).trim.toDouble)
    })
      .assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor[SensorReading](Time.seconds(1)) {
        override def extractTimestamp(t: SensorReading) = t.timestamp * 1000L
      })
    val sensorTable: Table = tableEnv.fromDataStream(dataStream, 'id, 'temperature, 'timestamp.rowtime as 'ts)

    //table ipa

    val split = new split("_")
    val resTable = sensorTable
      .joinLateral(split('id) as('word, 'length))
      .select('id, 'ts, 'word, 'length)


    //sql

    tableEnv.createTemporaryView("sensor", sensorTable)
    tableEnv.registerFunction("split", split)
    val sqlTabele: Table = tableEnv.sqlQuery(
      """
        |
        |select
        | id, ts, word, length
        |from
        | sensor,
        |lateral table(split(id)) as splitid(word,length)
        |""".stripMargin)

    resTable.toAppendStream[Row].print("result")
    sqlTabele.toAppendStream[Row].print("sql")
    env.execute("function test")


  }

  //自定义tableFunction

  class split(separator: String) extends TableFunction[(String, Int)] {

    def eval(str: String): Unit = {
      str.split(separator).foreach(
        (word: String) => {
          collect((word, word.length))
        }
      )
    }
  }
}
