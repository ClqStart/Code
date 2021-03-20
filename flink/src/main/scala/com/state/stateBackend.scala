package com.state

import java.util.concurrent.TimeUnit

import org.apache.flink.api.common.restartstrategy.RestartStrategies
import org.apache.flink.contrib.streaming.state.RocksDBStateBackend
import org.apache.flink.runtime.state.filesystem.FsStateBackend
import org.apache.flink.runtime.state.memory.MemoryStateBackend
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._
import org.apache.flink.api.common.time.Time


object stateBackend {

  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment
      .getExecutionEnvironment

//    env.setStateBackend(new MemoryStateBackend())
//    env.setStateBackend(new FsStateBackend("dfs://node101:8082")
//    env.setStateBackend(new RocksDBStateBackend("out/back"))

    //检查点设置
    env.enableCheckpointing(1000L)//启动检查点间隔1秒
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.AT_LEAST_ONCE)//设置检查点模式
    env.getCheckpointConfig.setCheckpointTimeout(60000L) //检查点保存超时时间时间一般为分钟
    env.getCheckpointConfig.setMaxConcurrentCheckpoints(2)  //设置同时几个barre进行
    env.getCheckpointConfig.setMinPauseBetweenCheckpoints(500) //设置barre间隔时间和上一个配置互斥
    env.getCheckpointConfig.setFailOnCheckpointingErrors(true)  //允许误差
    env.getCheckpointConfig.setTolerableCheckpointFailureNumber(0)  //等同允许误差
    env.getCheckpointConfig.setTolerableCheckpointFailureNumber(3)  //允许多少次误差


    //重启策略
    env.setRestartStrategy(RestartStrategies.fixedDelayRestart(3,1000L))
//    env.setRestartStrategy(RestartStrategies.fixedDelayRestart(3,Time.seconds(3000L))

    //env.setRestartStrategy(RestartStrategies.failureRateRestart(5,Time.of(5,TimeUnit.SECONDS),Time.of(10,TimeUnit.SECONDS))









    env.execute("state")
  }

}
