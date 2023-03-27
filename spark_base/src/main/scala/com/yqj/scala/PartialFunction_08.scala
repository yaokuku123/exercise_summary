package com.yqj.scala

object PartialFunction_08 {
  def main(args: Array[String]): Unit = {
    def func: PartialFunction[Any, String] = {
      case "hello" => s"value is hello"
      case x: Int => s"value $x is Int"
      case _ => "none"
    }

    println(func("hello"))
    println(func(10))
    println(func("yorick"))
  }
}
