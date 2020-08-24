package com.bigdata.spark.data

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object VarBroadcast {
  def main(args: Array[String]): Unit = {
    val Sparkconf = new SparkConf().setMaster("local[*]").setAppName("Var")
    val sc = new SparkContext(Sparkconf)

    val rdd: RDD[(Int, String)] = sc.makeRDD(List((1, "a"), (2, "b"), (3, "c")))

    val list = List((1, 1), (2, 2), (3, 3))

    val broadcast: Broadcast[List[(Int, Int)]] = sc.broadcast(list)

    val resultRDD: RDD[(Int, (String, Any))] = rdd.map {
      case (key, value) => {
        var v2: Any = null;
        for (t <- broadcast.value) {
          if (key == t._1) {
            v2 = t._2
          }
        }
        (key, (value, v2))
      }
    }
    resultRDD.foreach(println)

  }

}
