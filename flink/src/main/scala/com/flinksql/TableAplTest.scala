package com.flinksql

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.streaming.api.scala._
import org.apache.flink.table.api.{EnvironmentSettings, TableEnvironment}
import org.apache.flink.table.api.scala.{BatchTableEnvironment, StreamTableEnvironment}


class TableAplTest {
  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val tableEnv = StreamTableEnvironment.create(env)

    //1.1基于老版本planner的流处理
    val settings: EnvironmentSettings = EnvironmentSettings.newInstance()
      .useOldPlanner()
      .inStreamingMode()
      .build()
    val oldenvironment: StreamTableEnvironment = StreamTableEnvironment.create(env, settings)

    //1.2基于老版本planner批处理
    val batchEnv: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
    val oldBathTableEnv: BatchTableEnvironment = BatchTableEnvironment.create(batchEnv)

    //2.1基于新版本的流处理
    val blinkStreamSettings: EnvironmentSettings = EnvironmentSettings.newInstance()
      .useBlinkPlanner()
      .inStreamingMode()
      .build()

    StreamTableEnvironment.create(env,blinkStreamSettings)

    //2.2基于新版本planner的流处理
    val blinkBathSettings: EnvironmentSettings = EnvironmentSettings.newInstance()
      .useBlinkPlanner()
      .inBatchMode()
      .build()

//    TableEnvironment.create(blinkBathSettings)




  }

}
