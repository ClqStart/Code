package com.function

import com.tranfer.SensorReading
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.table.api.scala._
import org.apache.flink.table.api.{EnvironmentSettings, Table}
import org.apache.flink.table.functions.{AggregateFunction, TableAggregateFunction, TableFunction}
import org.apache.flink.table.plan.logical.rel.TableAggregate
import org.apache.flink.types.Row
import org.apache.flink.util.Collector

object AggtableFunction {
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
    val top2Temp = new Top2Temp()
    val reTeable: Table = sensorTable
      .groupBy('id)
      .flatAggregate(top2Temp('temperature) as ('temp,'rank))
      .select('id, 'temp,'rank)
    //sql

    tableEnv.createTemporaryView("sensor",sensorTable)
    tableEnv.registerFunction("top2Temp",top2Temp)



    reTeable.toRetractStream[Row].print("res")

    env.execute(" table function test")


  }
}


//第一个类函数聚合状态
class Top2TempAcc{
  var highestTemp: Double = Double.MinValue
  var SecondHighestTemp: Double = Double.MinValue

}

//自定义表聚合函数
class Top2Temp  extends  TableAggregateFunction[(Double,Int),Top2TempAcc]{
  override def createAccumulator(): Top2TempAcc = new Top2TempAcc
  //实现计算聚合结果的函数accumulate
  def accumulate(acc : Top2TempAcc,Temp:Double): Unit ={

    if(Temp >acc.highestTemp){
      acc.SecondHighestTemp = acc.highestTemp
      acc.highestTemp = Temp
    }else if(Temp > acc.SecondHighestTemp) {
      acc.SecondHighestTemp = Temp
    }
  }
  //实现一个输出结果的方法
  def  emitValue ( acc: Top2TempAcc,out:Collector[(Double,Int)]): Unit ={

    out.collect((acc.highestTemp,1))
    out.collect((acc.SecondHighestTemp,2))
  }

}
