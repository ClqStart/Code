package com.sink

import java.sql.{Connection, DriverManager, PreparedStatement}

import com.tranfer.SensorReading
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, _}


object JCDBSink {

  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)

    val stream: DataStream[String] = env.readTextFile("in/sensor.txt")

    val dataStream: DataStream[SensorReading] = stream.map(data => {
      val dataarray = data.split(",")
      SensorReading(dataarray(0).trim, dataarray(1).trim.toLong, dataarray(2).trim.toDouble)
    })

    dataStream.addSink(new MyJDBCSink())


    env.execute("jdbc sink")
  }
}

class MyJDBCSink() extends RichSinkFunction[SensorReading]() {


  //定义sql连接，预编译期
  var conn: Connection = _
  var insertStmt: PreparedStatement = _
  var updateStmt: PreparedStatement = _

  override def open(parameters: Configuration): Unit = {
    //创建连接
    super.open(parameters)
    conn = DriverManager.getConnection("jdbc:mysql:///mp", "root", "123")
    insertStmt = conn.prepareStatement("INSERT into temperatures (sensor,temp) values (?,?)")
    updateStmt = conn.prepareStatement("UPDATE temperatures SET temp=? WHERE sensor=?")
  }

  override def invoke(value: SensorReading, context: SinkFunction.Context[_]): Unit = {

    updateStmt.setDouble(1, value.temperature)
    updateStmt.setString(2, value.id)
    updateStmt.execute()
    //判断是否更新
    if (updateStmt.getUpdateCount == 0) {
      insertStmt.setDouble(2, value.temperature)
      insertStmt.setString(1, value.id)
      insertStmt.execute()
    }
  }
  //关闭
  override def close(): Unit = {
    insertStmt.close()
    updateStmt.close()
    conn.close()
  }
}
