package com.source



import org.apache.flink.streaming.api.datastream.DataStreamSource
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.api.functions.source.SourceFunction

import scala.collection.immutable
import scala.util.Random

//自定义数据源

object SensorReadUDF {
  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    val stream: DataStreamSource[SensorReadinng] = env.addSource(new SensorSource)

    stream.print("UDF").setParallelism(1)
    env.execute()
  }
}

class SensorSource()extends SourceFunction[SensorReadinng] {

  var running =true
  override def run(sourceContext: SourceFunction.SourceContext[SensorReadinng]): Unit = {
    val rand = new Random()
    var curTemp: immutable.Seq[(String, Double)] = 1.to(10).map(
      i=>("aensor_"+i,60+rand.nextGaussian()*20)
    )

    while (running){
       curTemp.map(
         t=>(t._1,t._2+rand.nextGaussian())
       )

      val curTime: Long = System.currentTimeMillis()
      curTemp.foreach(
        t=> sourceContext.collect(SensorReadinng(t._1,curTime,t._2))
      )
      Thread.sleep(1000)
    }


  }

  override def cancel(): Unit = {

    running=false
  }
}
