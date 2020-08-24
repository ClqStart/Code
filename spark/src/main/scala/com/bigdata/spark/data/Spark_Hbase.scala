package com.bigdata.spark.data
import org.apache.spark.SparkConf
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.{Cell, CellUtil, HBaseConfiguration}
import org.apache.spark.SparkContext
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.client.Result
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.rdd.RDD

object Spark_Hbase {
  def main(args: Array[String]): Unit = {



    val Sparkconf = new SparkConf()
    val hbaseConf: SparkConf = Sparkconf.setMaster("local[*]").setAppName("hbase")
    val sc = new SparkContext(hbaseConf)

    val conf: Configuration = HBaseConfiguration.create()
    conf.set("hbase.zookeeper.quorum", "node102,node103,node104")
    conf.set(TableInputFormat.INPUT_TABLE, "student")
    val hbaseResult: RDD[(ImmutableBytesWritable, Result)] = sc.newAPIHadoopRDD(
      conf,
      classOf[TableInputFormat],
      classOf[ImmutableBytesWritable],
      classOf[Result]
    )
    hbaseResult.foreach{
      case (rowkey,result)=>{
        val cells: Array[Cell] = result.rawCells()
        for(cell <- cells){
          println( Bytes.toString(CellUtil.cloneValue(cell)))
        }
      }
    }



  }

}
