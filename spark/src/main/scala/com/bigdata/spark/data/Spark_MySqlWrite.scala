package com.bigdata.spark.data


import org.apache.spark.{SparkConf, SparkContext}
import java.sql.{Connection, PreparedStatement}

object Spark_MySqlWrite {

  def main(args: Array[String]): Unit = {


    import org.apache.spark.rdd.RDD
    val conf = new SparkConf().setMaster("local[*]").setAppName("data")
    val sc = new SparkContext(conf)


    //3.定义连接mysql的参数
    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://localhost:3306/user_db"
    val userName = "root"
    val passWd = "123"

    val dataRdd: RDD[(Int, String, String)] = sc.makeRDD(List((3, "书籍", "C"), (4, "电影", "D")))


//    dataRdd.foreach({
//      case (id: Int, username: String, status: String) => {
//
//
//        Class.forName(driver)
//        val sql = "insert into t_book (user_id,username,ustatus) values (?,?,?)"
//        val statement: PreparedStatement = connection.prepareStatement(sql)
//        statement.setInt(1, id)
//        statement.setString(2, username)
//        statement.setString(3, status)
//        statement.execute()
//
//        statement.close()
//      }
//    })
    dataRdd.foreachPartition(datas=>{
      val connection: Connection = java.sql.DriverManager.getConnection(url, userName, passWd)
      datas.foreach{
        case (id: Int, username: String, status: String) => {
          Class.forName(driver)
          val sql = "insert into t_book (user_id,username,ustatus) values (?,?,?)"
          val statement: PreparedStatement = connection.prepareStatement(sql)
          statement.setInt(1, id)
          statement.setString(2, username)
          statement.setString(3, status)
          statement.execute()

          statement.close()
        }
      }
      connection.close()
    }

    )

    sc.stop()

  }

}
