package com.tranfer

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, _}


object ConnectCoMap {

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

    val warning: DataStream[(String, Double)] = high.map(data=>(data.id,data.temperature))

    val connectStream: ConnectedStreams[(String, Double), SensorReading] = warning.connect(low)
    val res: DataStream[Product] = connectStream.map(
      warningdata => (warningdata._1, warningdata._2, "warning"),
      lowdata => (lowdata.id, "healthy")
    )
    res.print("conn")


    env.execute("co")

  }

}
