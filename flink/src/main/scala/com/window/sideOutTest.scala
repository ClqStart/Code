package com.window

import org.apache.flink.api.common.state.ValueStateDescriptor
import org.apache.flink.streaming.api.functions.{KeyedProcessFunction, ProcessFunction}
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.util.Collector


object sideOutTest {

  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)


    val stream: DataStream[String] = env.socketTextStream("node101", 7777)
    val dataStream: DataStream[SensorReading] = stream.map(data => {
      val dataarray = data.split(",")
      SensorReading(dataarray(0).trim, dataarray(1).trim.toLong, dataarray(2).trim.toDouble)
    })
      //设置watermark
      .assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor[SensorReading](Time.seconds(1)) {
        override def extractTimestamp(element: SensorReading): Long = element.timestamp * 1000
      })

    val preoceDStream: DataStream[SensorReading] = dataStream
      .process(new FrezzingAlert())

    preoceDStream.print("main out")
    preoceDStream.getSideOutput(new OutputTag[(String,Long,Double)]("side")).print("side out")


    env.execute()
  }

}

class FrezzingAlert extends ProcessFunction[SensorReading, SensorReading] {
  override def processElement(value: SensorReading, ctx: ProcessFunction[SensorReading, SensorReading]#Context, out: Collector[SensorReading]): Unit = {

    lazy val alertOutput = new OutputTag[(String,Long,Double)]("side")
    if (value.temperature < 32) {

      ctx.output(alertOutput,(value.id,value.timestamp,value.temperature))
    } else {
      out.collect(value)
    }

  }
}


