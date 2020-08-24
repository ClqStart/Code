package com.bigdata.scala

/**
 * val 常量
 * var 变量
 */
object Test {
  var a=3
  private val ox:ooxx = new ooxx()

  private  val ox1= new ooxx("lisi")
  println("test...up..")
  def main(args: Array[String]): Unit={
    println(s"hello word...${ox1.name}")
    ox.senmsg()
    println("-------------------------------")
    val  xo = new xxoo("女")
    val  xo1 = new xxoo("王五","女")
    xo.printMsg()
    xo1.printMsg()
  }
  println("test...down..")

}
class ooxx{
  var name = "class..ooxx"
  def this(xname:String){
    this
    name = xname
  }
  println("ooxx..up")
  def senmsg(): Unit ={
    println(s"伴生关系....${ooxx.nameooxx}")
    println(s"send massage.......$name")
  }
  println("ooxx..down")
}
/*
object和clas同名为伴生关系，可以对象和静态相互访问
 */
object ooxx{
 var name="onject...oooxx"
 private val nameooxx = "age"
}
/**
 * 类名构造器中的参数就是类的成员属性，且默认是val类型，private的
 * 类名构造器中的参数可以设置成val和var,其他方法函数中的参数都是val,不允许设置成var类型
 * @param sex
 */
class xxoo(sex:String){



  def this(xname:String,sex:String){
    this(sex)
    name =xname
  }


  var name = "xxoo"
  def printMsg(): Unit = {
    println(s"sex...$sex......${ooxx.name}")
  }

}
