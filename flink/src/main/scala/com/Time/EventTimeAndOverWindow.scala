package com.Time

import com.tranfer.SensorReading
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.table.api.scala._
import org.apache.flink.table.api.{EnvironmentSettings, Over, Table, Tumble}
import org.apache.flink.types.Row

object EventTimeAndOverWindow {
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

    //over Window

    //统计每条sensor数据与之前2条数据的平均温度

    //1.table api
    val resTable: Table = sensorTable
      .window(Over partitionBy 'id orderBy 'ts preceding 2.rows as 'ow)
      .select('id,'ts,'id.count over 'ow, 'temperature.avg over 'ow)


    //2. sql
    tableEnv.createTemporaryView("sensor", sensorTable)
    val resSQlTable: Table = tableEnv.sqlQuery(
      """
        |select
        |  id,
        |  ts,
        |  count(id) over ow,
        |  avg(temperature) over ow
        | from sensor
        | window ow as (
        |  partition by id
        |  order by  ts
        |  rows between 2 preceding and current row
        | )
        |""".stripMargin)

    resTable.toAppendStream[Row].print("res")
    resSQlTable.toRetractStream[Row].print("sql")


    env.execute("time and window test")


  }
}
