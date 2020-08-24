package com.bigdata.scala

import java.util

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object lesson_Collections {
  def main(args: Array[String]): Unit = {
    val listJava = new util.LinkedList[String]()
    listJava.add("h")
    listJava.add("a")
    listJava.add("b")
    listJava.add("c")
    listJava.add("d")

    for (i <- 0 until listJava.size()) {
      print(listJava.get(i))
    }
    val arr01 = Array[Int](1, 2, 3, 4, 6)
    print(arr01(4))

    for (a <- arr01) {
      print(a)
    }
    println()
    arr01(4) = 5
    arr01.foreach(print)
    println("-------------------------------------------list------------------------------")
    val list02 = new ListBuffer[Int]()
    list02.+=(33)
    list02.+=(22)
    list02.+=(1)
    list02.foreach(print)

    println("----------------------tuple----------------")

    val t2 = new Tuple2(11, "asddf")
    val t3 = Tuple3(22, "assdd", "a")
    val t4: (Int, Int, Int, Int) = (1, 2, 3, 4)
    val tuple = ((a: Int) => a + 8, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2)
    println(tuple._1(8))
    println(t2._1)
    println(t3._3)
    val tIter: Iterator[Any] = tuple.productIterator
    while (tIter.hasNext) {
      println(tIter.next())
    }

    println("-----------------------immutable map--------------------")
    val map01: Map[String, Int] = Map(("a", 33), "b" -> 22, ("c", 11), ("a", 33333))
    val keys = map01.keys

    //get获得一个option（ none和same）
    println(map01.get("a").get)
    println(map01("a"))
    println(map01.get("w"))
    println(map01.getOrElse("w", "hello"))
    println(map01.get("w").getOrElse("hello"))

    for (elem <- keys) {
      println(s"$elem  ${map01(elem)}")
    }
    //    //报错
    //    println(map01.get("w").get)
    //    println(map01("w"))

    val map02: mutable.Map[String, Int] = scala.collection.mutable.Map(("a", 33), "b" -> 22, ("c", 11))

    map02.put("c",33333333)

    println("------------------------list------------")

    val list = List(1, 2, 3, 4, 5, 6)
    list.foreach(print(_, " "))
    println()
    val listMap: List[Int] = list.map((x: Int) => x + 10)
    listMap.foreach(print(_, " "))
    println()
    val listMap2: List[Int] = list.map(_ * 10)
    listMap2.foreach(print(_, " "))
    println()
    list.foreach(print(_, " "))
    println()

    println("------------------------升华----------------------")
    val listStr = List(
      "hello word",
      "hello msb",
      "good idea"
    )

    val flatMap: List[String] = listStr.flatMap((x: String) => x.split(" "))
    flatMap.foreach(print(_," "))
    println()
    val mapList = flatMap.map((_, 1))
    mapList.foreach(print(_))
    /*
     *问题：内存膨胀n被，如何替换中间数据占用的内存问题
     */
    println()
    println("---------------------------用迭代器升华---------------")

    val iter :Iterator[String] = listStr.iterator
    val iterflatMap: Iterator[String] = iter.flatMap((x: String) => x.split(" "))
    //iterflatMap.foreach(print(_," "))
    val itermapList = iterflatMap.map((_, 1))
    itermapList.foreach(print)

  }

}
