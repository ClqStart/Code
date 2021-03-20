package com.state

import java.util

import com.tranfer.SensorReading
import org.apache.flink.api.common.functions.{ReduceFunction, RichMapFunction}
import org.apache.flink.api.common.state.{ListState, ListStateDescriptor, MapState, MapStateDescriptor, ReducingState, ReducingStateDescriptor, ValueState, ValueStateDescriptor}
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, _}


object windowTest {

  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)
//    val stream: DataStream[String] = env.readTextFile("in/sensor.txt")

    val stream: DataStream[String] = env.socketTextStream("node101", 7777)
    val dataStream: DataStream[SensorReading] = stream.map(data => {
      val dataarray = data.split(",")
      SensorReading(dataarray(0).trim, dataarray(1).trim.toLong, dataarray(2).trim.toDouble)
    })



    env.execute("keyed state")

  }

}
//自定义函数
class  MyRichMapper extends  RichMapFunction[SensorReading,String]{
  var valueState: ValueState[Double] =_
  lazy  val  listState: ListState[Int]  = getRuntimeContext.getListState(new ListStateDescriptor[Int]("listState",classOf[Int]))
  lazy val   mapState: MapState[String,Double] = getRuntimeContext.getMapState(new MapStateDescriptor[String,Double]("mapState",classOf[String],classOf[Double]))
  lazy val  reduceState: ReducingState[SensorReading] = getRuntimeContext.getReducingState(new ReducingStateDescriptor[SensorReading]("reduceState",new ReduceFunction[SensorReading] {
    override def reduce(value1: SensorReading, value2: SensorReading) = {
      SensorReading(value1.id,value2.timestamp,value1.temperature.min(value2.temperature))
    }
  },classOf[SensorReading]))

  override def map(value: SensorReading): String = {

    val valuere: Double = valueState.value()


    listState.add(1)
    listState.add(2)
    listState.add(3)
    val list=new util.ArrayList[Int]()
    list.add(1)
    list.add(2)
    list.add(3)
    listState.addAll(list)
    listState.update(list)
    listState.get()


    mapState.contains("sensor_1")
    mapState.get("sensor_1")
    mapState.put("sensor_1",1.4)

    reduceState.add(value)
    reduceState.get()

    value.id
  }

  override def open(parameters: Configuration): Unit = {
    valueState = getRuntimeContext.getState(new ValueStateDescriptor[Double]("valuestate", classOf[Double]))

  }
}
