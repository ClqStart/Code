
import org.apache.flink.api.scala._

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala.DataStream

object StreamWC {

  def main(args: Array[String]): Unit = {

    val sfenv: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    val dataStream: DataStream[String] = sfenv.socketTextStream("node101", 7777)

    val res: DataStream[(String, Int)] = dataStream.flatMap(_.split(" ")).filter(_.nonEmpty).map((_, 1)).keyBy(0).sum(1)
        res.print()

    sfenv.execute()
  }
}
