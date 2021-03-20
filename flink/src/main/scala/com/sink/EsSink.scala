package com.sink

import java.util

import com.sun.jndi.cosnaming.CNCtx
import com.tranfer.SensorReading
import org.apache.flink.api.common.functions.RuntimeContext
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, _}
import org.apache.flink.streaming.connectors.elasticsearch.{ElasticsearchSinkFunction, RequestIndexer}
import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink
import org.apache.flink.table.descriptors.Elasticsearch
import org.apache.http.HttpHost
import org.elasticsearch.client.Requests

object EsSink {

  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)
    val stream: DataStream[String] = env.readTextFile("in/sensor.txt")

    val dataStream: DataStream[SensorReading] = stream.map(data => {
      val dataarray = data.split(",")
      SensorReading(dataarray(0).trim, dataarray(1).trim.toLong, dataarray(2).trim.toDouble)
    })

    val httpHosts = new util.ArrayList[HttpHost]()
    httpHosts.add(new HttpHost("localhost", 9200))

    val elasticseachsink = new ElasticsearchSink.Builder[SensorReading](
      httpHosts,
      new ElasticsearchSinkFunction[SensorReading] {
        override def process(element: SensorReading, ctx: RuntimeContext, Indexer: RequestIndexer): Unit = {

          println("save data：" + element)

          //包装数据
          val json = new util.HashMap[String, String]()
          json.put("sensor_id", element.id)
          json.put("sensor_temperature", element.temperature.toString)
          json.put("sensor_timestamp", element.timestamp.toString)
          //创建请求
          val indexRequest = Requests.indexRequest()
            .index("sensor")
            .`type`("readingdata")
            .source(json)
          //利用index准备发送请求
          Indexer.add(indexRequest)
          printf("data saved")
        }
      })

    dataStream.addSink(elasticseachsink.build())


    env.execute("redis sink")

  }

}

