package com.bigdata.spark.data

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapred.TableOutputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.mapred.JobConf
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Spark_HbaseWrite {
  def main(args: Array[String]): Unit = {


    val Sparkconf = new SparkConf()
    val hbaseConf: SparkConf = Sparkconf.setMaster("local[*]").setAppName("hbaseW")
    val sc = new SparkContext(hbaseConf)

    val conf: Configuration = HBaseConfiguration.create()
    conf.set("hbase.zookeeper.quorum", "node102,node103,node104")


    val dataRDD: RDD[(String, String)] = sc.makeRDD(List(("1002", "lishi"), ("1003", "wangwu"), ("1004", "zhaoliu")))

    val putRDD: RDD[(ImmutableBytesWritable, Put)] = dataRDD.map({
      case (rowkey, name) => {
        val put = new Put(Bytes.toBytes(rowkey))
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes(name))
        (new ImmutableBytesWritable(Bytes.toBytes(rowkey)), put)
      }
    })

    val jobConf = new JobConf(conf)
    jobConf.setOutputFormat(classOf[TableOutputFormat])
    jobConf.set(TableOutputFormat.OUTPUT_TABLE, "student")
    putRDD.saveAsHadoopDataset(jobConf)
  }

}
