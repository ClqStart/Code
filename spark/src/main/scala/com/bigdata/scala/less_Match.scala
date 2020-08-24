package com.bigdata.scala

import java.util

object less_Match {
  def main(args: Array[String]): Unit = {
    val tup = (1.0, 88, "abc", false, 99)
    val iter = tup.productIterator
    val res = iter.map {
      //      x=> {
      //        x match {
      //          case 1 => println(s"$x...is 1")
      //          case 88 => println(s"$x ...is 88")
      //          case false => println(s"$x... is false")
      //          case w: Int if w > 50 => println(s"$w..is  > 50")
      //          case _ => println("----------------------")
      //        }
      case x@1 => println(s"$x...is 1")
      case x@88 => println(s"$x ...is 88")
      case x@false => println(s"$x... is false")
      case w: Int if w > 50 => println(s"$w..is  > 50")
      case _ => println("----------------------")
    }
    while (res.hasNext) println(res.next())


    println("-------------------偏函数---------------------------------------")

    def aaa: PartialFunction[Any, String] = {
      case "hello" => "val is hello"
      case x: Int => s"$x... is Int"
      case _ => "None"
    }

    println(aaa(2))
    println(aaa("hello"))
    println(aaa)
    println(aaa("ddd"))
    println("-------------------隐式转换-----------------------------")


    val list = new util.LinkedList[Int]()
    list.add(1)
    list.add(2)
    list.add(3)
    //    list.forEach(println(_))

    def forEach[T](list: util.LinkedList[T], f: (T) => Unit): Unit = {
      val iter: util.Iterator[T] = list.iterator()
      while (iter.hasNext) f(iter.next())
    }

    forEach(list, println)

    val xx = new XXX(list)
    xx.foreach(println)

    println("----------------------------隐式转换最终结果---------------------------")

    /*
    1,scala编译器发现 list.foreach(println)  有bug
    2,去寻找有没有implicit  定义的方法，且方法的参数正好是list的类型！！！
    3,编译期：完成你曾经人类：
    */
    implicit def sdfsdf[T](list: util.LinkedList[T]): XXX[T] = {
      new XXX(list)
    }

    list.foreach(println)

    println("---------------------------------隐士类------------------------")
    implicit class KKK[T](list: util.LinkedList[T]) {
      def foreach1(f: (T) => Unit): Unit = {
        val iter: util.Iterator[T] = list.iterator()
        while (iter.hasNext) f(iter.next())
      }
    }

    list.foreach1(println)
    println("---------------------------------------------------------------------------")

    implicit  def abcd[T](list:util.ArrayList[T]): XXX1[T]={
      val iter: util.Iterator[T] = list.iterator()
      new XXX1(iter)
    }


    implicit  def abcd2[T](list:util.LinkedList[T]): XXX1[T]={
      val iter: util.Iterator[T] = list.iterator()
      new XXX1(iter)
    }

    val list1 = new util.ArrayList[Int]()
    list1.add(1)
    list1.add(2)
    list1.add(3)
//
    list.foreachall(println)
    list1.foreachall(println)
    println("------------------------------隐式转换参数--------------------------")
    implicit val  aa:Int =88
    implicit val ss = "lisi"
    def ssss(implicit name:String,age:Int):Unit={
      println(name+" "+age)
    }
    ssss("lishi",13)
    ssss
    def ssdd(age: Int)(implicit name:String):Unit={
      println(name+" "+age)
    }
    ssdd(12)
    ssdd(11)("王五")
  }
}
class XXX1[T](iter: util.Iterator[T]){
  def foreachall(f: (T) => Unit): Unit = {
    while (iter.hasNext) f(iter.next())
  }
}

class XXX[T](list: util.LinkedList[T]) {

  def foreach(f: (T) => Unit): Unit = {
    val iter: util.Iterator[T] = list.iterator()
    while (iter.hasNext) f(iter.next())
  }
}



