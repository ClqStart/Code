package com.bigdata.scala

import java.util.Date

import scala.collection.immutable


object lesson {

  def main(args: Array[String]): Unit = {
    val seq = Range.inclusive(1, 10, 2)
    for (i <- seq) {
      println(i, " ")
    }
    print(seq)
    println()
    val seq1: Range.Inclusive = 1 to 10
    for (i <- seq1 if (i % 2) == 0) {
      println(i, " ")
    }
    print(seq1)
    println()

    val seq2: Range.Inclusive = 1 to(10, 3)
    print(seq2)
    println()
    println("-------------foreach-----------------")
    seq2.foreach(print(_))
    println()
    seq2.foreach(print)
    println()

    println("------------------------------")

    val seq3 = 1 until(10, 4)
    print(seq3)
    println()


    println("------------------------------------")
    for (i <- 1 to 9) {
      for (j <- 1 to 9) {
        if (i == j) println()
        if (i <= j) print(s"$i+$j=${i + j}\t")
      }
    }

    println("--------------2----------------------")
    for (i <- 1 to 9; j <- 1 to 9 if (i >= j)) {
      if (j <= i) print(s"$i+$j=${i + j}\t")
      if (i == j) println()
    }
    println("-------------------------3-------------")

    val ints: immutable.IndexedSeq[Int] = for (i <- 1 to 10) yield i
    println(ints)

    val v: immutable.IndexedSeq[Int] = for (i <- 1 to 10) yield {
      val a = 8
      i + a
    }
    println(v)

    println("-------------------匿名函数-------------")
    val y = (a: Int, b: Int) => {
      a + b
    }
    println(y(3, 4))

    val yy: (Int, Int) => Int = (c: Int, d: Int) => {
      c + d
    }
    println(s"yy: ${yy(4, 8)}")
    println("--------------------------------偏应用函数-------fun------")

    def func(date: Date, tp: String, msg: String): Unit = {
      println(s"$date\t$tp\t$msg")
    }

    func(new Date(), "info", "ok")

    val infoFun = func(_: Date, "info", _: String)
    infoFun(new Date(), "少了一个参数")

    println("-------------------可变参数---------------")

    def fun3(a: Int*): Unit = {
      for (e <- a) {
        println(e)
      }
      a.foreach((x: Int) => {
        println(x)
      })
    }

    fun3(1, 2, 3, 4, 5, 6, 7)
    fun3(1, 2, 3)

    println("---------------------高阶参数---------")

    //函数作为参数，函数作为返回值
    //1.函数作为参数
    def computer(a: Int, b: Int, f: (Int, Int) => Int): Unit = {
      val res: Int = f(a, b)
      println(res)
    }

    computer(3, 8, (x: Int, y: Int) => {
      x + y
    })
    computer(3, 8, (x: Int, y: Int) => {
      x * y
    })
    computer(3, 8, _ * _)

    println("----------------返回值-----------------------")

    //返回值为函数
    def factory(i: String): (Int, Int) => Int = {
      def plus(x: Int, y: Int): Int = {
        x + y
      }

      if (i.equals("+")) {
        plus
      } else {
        (x: Int, y: Int) => {
          x * y
        }
      }
    }

    computer(3, 8, factory("*"))

    println("---------------科里化------")

    def fun5(a: Int*)(b: String*)(c: Char*): Unit = {
      a.foreach(print)
      println()
      b.foreach(print(_))
      println()
      c.foreach((x: Char) => {
        print(x)
      })
      println()
    }

    fun5(1, 2, 3, 4)("a", "ddd", "fg")('1', 'd', ']')

    println("------------------------方法引用----------------")

    /**
     * 方法引用生效与不生效，空格加_方法不执行
     */
    def xyx(): Unit ={
      println("xyx")
    }
    val fun = xyx
    val fun1 = xyx _


  }
}





