import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.api.scala._

object WC {

  def main(args: Array[String]): Unit = {

    val fenv: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment

    val ds: DataSet[String] = fenv.readTextFile("in/data.txt")

    val res: AggregateDataSet[(String, Int)] = ds.flatMap(_.split(" ")).map((_, 1)).groupBy(0).sum(1)

    res.print()
  }

}
