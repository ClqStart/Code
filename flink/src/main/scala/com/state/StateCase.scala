package com.state

import com.tranfer.SensorReading
import org.apache.flink.api.common.functions.RichFlatMapFunction
import org.apache.flink.api.common.state._
import org.apache.flink.api.common.time.Time
import org.apache.flink.streaming.api.scala._
import org.apache.flink.util.Collector

object StateCase {

  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)


    val stream: DataStream[String] = env.socketTextStream("node101", 7777)
    val dataStream: DataStream[SensorReading] = stream.map(data => {
      val dataarray = data.split(",")
      SensorReading(dataarray(0).trim, dataarray(1).trim.toLong, dataarray(2).trim.toDouble)
    })


    //每15s统计一次，窗口函数中的最小值,以及当前时间，近视对15s内的数据进行操作
    val res: DataStream[(String, Double, Double)] = dataStream
      .keyBy(_.id)
      .flatMap(new TempChangeAlert1(10.0))

    dataStream.print("data")
    res.print("flatMap")

    env.execute("test")
  }

}
// 实现自定义RichFlatmapFunction
class TempChangeAlert1(threshold: Double) extends RichFlatMapFunction[SensorReading, (String, Double, Double)]{
  // 定义状态保存上一次的温度值
  lazy val lastTempState: ValueState[Double] = getRuntimeContext.getState(new ValueStateDescriptor[Double]("last-temp", classOf[Double]))
  lazy val flagState: ValueState[Boolean] = getRuntimeContext.getState(new ValueStateDescriptor[Boolean]("flag", classOf[Boolean]))

  override def flatMap(value: SensorReading, out: Collector[(String, Double, Double)]): Unit = {
    // 获取上次的温度值
    val lastTemp = lastTempState.value()
    // 跟最新的温度值求差值作比较
    val diff = (value.temperature - lastTemp).abs
    if(flagState.value() && diff > threshold ) {
      out.collect( (value.id, lastTemp, value.temperature) )
        // 更新状态
  }
    lastTempState.update(value.temperature)
    flagState.update(true)
  }

}

class TempChangeAlert(threshold: Double) extends RichFlatMapFunction[SensorReading, (String, Double, Double)]{
  // 定义状态保存上一次的温度值
  lazy val lastTempState: ValueState[Double] = getRuntimeContext.getState(new ValueStateDescriptor[Double]("last_temp", classOf[Double]))
  override def flatMap(value: SensorReading, out: Collector[(String, Double, Double)]): Unit = {
    // 获取上次的温度值
    val lastTemp = lastTempState.value()
    // 跟最新的温度值求差值作比较
    val diff = (value.temperature - lastTemp).abs
    if(diff > threshold )
      out.collect( (value.id, lastTemp, value.temperature) )
    // 更新状态
    lastTempState.update(value.temperature)
  }

}


