package com.sink

import com.tranfer.SensorReading
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.redis._
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig
import org.apache.flink.streaming.connectors.redis.common.mapper.{RedisCommand, RedisCommandDescription, RedisMapper}

object RedisSink {

  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)
    val stream: DataStream[String] = env.readTextFile("in/sensor.txt")

    val dataStream: DataStream[SensorReading] = stream.map(data => {
      val dataarray = data.split(",")
      SensorReading(dataarray(0).trim, dataarray(1).trim.toLong, dataarray(2).trim.toDouble)
    })

    val conf: FlinkJedisPoolConfig = new FlinkJedisPoolConfig.Builder()
        .setHost("node105")
        .setPort(6379)
        .build()

    dataStream.addSink(new RedisSink(conf,new myRedisMapper))

    env.execute("redis sink")

  }

  class myRedisMapper extends RedisMapper[SensorReading]{

    override def getCommandDescription: RedisCommandDescription = {
      new RedisCommandDescription(RedisCommand.HSET,"sensor_temperature")

    }

    override def getKeyFromData(t: SensorReading): String = {
       t.id
    }

    override def getValueFromData(t: SensorReading): String = {
      t.temperature.toString
    }
  }

}
