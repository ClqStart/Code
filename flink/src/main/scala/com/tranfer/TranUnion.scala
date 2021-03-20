package com.tranfer

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, _}

/**
  * 1 Union之前两个流的类型必须是一样，Connect可以不一样，在之后的coMap中再去调整成为一样的。
  * 2	Connect只能操作两个流，Union可以操作多个
  */
//相同类型进行合并
object TranUnion {

  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)
    val stream: DataStream[String] = env.readTextFile("in/sensor.txt")
    val dataStream: DataStream[SensorReading] = stream.map(data => {
      val dataarray = data.split(",")
      SensorReading(dataarray(0).trim, dataarray(1).trim.toLong, dataarray(2).trim.toDouble)
    })

    val splitStream: SplitStream[SensorReading] = dataStream.split(data => {
      if (data.temperature > 30) Seq("high") else Seq("low")
    })

    val high: DataStream[SensorReading] = splitStream.select("high")
    val low: DataStream[SensorReading] = splitStream.select("low")

    val unionData: DataStream[SensorReading] = high.union(low)
    unionData.print("union")

    env.execute()

  }

}
