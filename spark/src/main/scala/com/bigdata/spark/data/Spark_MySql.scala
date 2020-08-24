package com.bigdata.spark.data

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark_MySql {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("data")
    val sc = new SparkContext(conf)


    //3.定义连接mysql的参数
    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://localhost:3306/mp"
    val userName = "root"
    val passWd = "123"
    val sql="select * from tb_user where id >=? and id <=?"
    val jdbcrdd = new JdbcRDD(
      sc,
      //获取数据库连接对象
      () => {
        Class.forName(driver)
        java.sql.DriverManager.getConnection(url, userName, passWd)
      },
      sql,
      1,
      5,
      2,
      (rs)=>{
        println(rs.getString(2)+","+rs.getInt(5))
      }
    )
    jdbcrdd.collect()

    sc.stop()

  }

}
