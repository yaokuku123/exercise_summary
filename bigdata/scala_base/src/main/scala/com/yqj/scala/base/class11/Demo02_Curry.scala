package com.yqj.scala.base.class11


object Demo02_Curry {
  def merge1(s1: String, s2: String, func: (String, String) => String): String = func(s1, s2)

  def merge2(s1: String, s2: String)(func: (String, String) => String): String = func(s1, s2)

  def main(args: Array[String]): Unit = {
    // 闭包
    // 可以获取函数以外的值
    val x = 10
    val getSum = (y: Int) => x + y
    println(getSum(2))
    println("-" * 10)

    // 柯里化
    // 普通方式
    val str1 = merge1("abc", "xyz", _ + _)
    println(str1)
    // 柯里化方式
    // 柯里化实际上是闭包的一种表现形式
    val str2 = merge2("abc", "xyz")(_ + _)
    println(str2)
    val str22 = merge2("abc", "xyz")(_.toUpperCase + _)
    println(str22)
    // 说明：柯里化后使得调用更加灵活，比如可以先传递前面的参数，返回一个函数，根据不同的情形调用不同的函数
    val curryFunc = merge2("abc", "XYZ") _ // method => func
    val choice = "lower"
    choice match {
      case "normal" => println(curryFunc(_ + _))
      case "upper" => println(curryFunc(_.toUpperCase() + _.toUpperCase()))
      case "lower" => println(curryFunc(_.toLowerCase() + _.toLowerCase()))
    }
    println("-" * 10)
  }
}
