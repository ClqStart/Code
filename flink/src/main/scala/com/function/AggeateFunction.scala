package com.function

import com.tranfer.SensorReading
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.table.api.scala._
import org.apache.flink.table.api.{EnvironmentSettings, Table}
import org.apache.flink.table.functions.{AggregateFunction, TableFunction}
import org.apache.flink.types.Row

object AggFunction {
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
    val avgTemp = new AvgTemp()
    val reTeable: Table = sensorTable
      .groupBy('id)
      .aggregate(avgTemp('temperature) as 'avgTemp)
      .select('id, 'avgTemp)


    //sql

    tableEnv.createTemporaryView("sensor",sensorTable)
    tableEnv.registerFunction("avgTemp",avgTemp)

    val sqlTabele: Table = tableEnv.sqlQuery(
      """
        |select
        | id,avgTemp(temperature)
        |from
        | sensor
        |group by
        | id
        |""".stripMargin)


    reTeable.toRetractStream[Row].print("res")
    sqlTabele.toRetractStream[Row].print("sql")
    env.execute(" agg function test")


  }
}

class AvgTempAcc {
  var sum: Double = 0.0
  var count: Int = 0
}

//自定义tableFunction ,(tempSum,temCount)
class AvgTemp extends AggregateFunction[Double, AvgTempAcc] {


  override def getValue(acc: AvgTempAcc): Double = acc.sum / acc.count

  override def createAccumulator(): AvgTempAcc = new AvgTempAcc

  //处理计算函数
  def accumulate(accumulate: AvgTempAcc, temp: Double): Unit = {
    accumulate.sum += temp
    accumulate.count += 1
  }

}
