package com.Time

import com.tranfer.SensorReading
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.table.api.scala._
import org.apache.flink.table.api.{EnvironmentSettings, Table, Tumble}
import org.apache.flink.types.Row

object EventTimeAndGropWindow {
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

    //Group Window

    //1.table api
    val resTable: Table = sensorTable
      .window(Tumble over 10.seconds on 'ts as 'tw) //每10秒滚动一次，滚动时间窗口
      .groupBy('id, 'tw)
      .select('id, 'id.count, 'temperature.avg, 'tw.end)



    //2. sql
    tableEnv.createTemporaryView("sensor",sensorTable)
    val resSQlTable: Table = tableEnv.sqlQuery(
      """
        |select
        | id,
        | count(id),
        | tumble_end(ts,interval '10' second)
        | from sensor
        | group by
        | id,
        | tumble(ts,interval '10' second)
        |""".stripMargin)

    resTable.toAppendStream[Row].print("res")
    resSQlTable.toRetractStream[Row].print("sql")


    env.execute("time and window test")


  }
}
