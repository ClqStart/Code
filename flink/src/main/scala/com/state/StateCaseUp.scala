package com.state

import com.tranfer.SensorReading
import org.apache.flink.streaming.api.scala._


object StateCaseUP {

  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)


    val stream: DataStream[String] = env.socketTextStream("node101", 7777)
    val dataStream: DataStream[SensorReading] = stream.map(data => {
      val dataarray = data.split(",")
      SensorReading(dataarray(0).trim, dataarray(1).trim.toLong, dataarray(2).trim.toDouble)
    })
      .uid("sava point")


    //每15s统计一次，窗口函数中的最小值,以及当前时间，近视对15s内的数据进行操作
    val res: DataStream[(String, Double, Double)] = dataStream
      .keyBy(_.id)
      .flatMapWithState[(String, Double, Double), Double] {
        case (data: SensorReading, None) => (List.empty, Some(data.temperature))
        case (data: SensorReading, lastTemp: Some[Double]) => {
          val diff = (data.temperature - lastTemp.get).abs
          if (diff > 10.0)
            (List((data.id, lastTemp.get, data.temperature)), Some(data.temperature))
          else
            (List.empty, Some(data.temperature))
        }
      }


    dataStream.print("data")
    res.print("flatMapWithState")

    env.execute("test")
  }

}


