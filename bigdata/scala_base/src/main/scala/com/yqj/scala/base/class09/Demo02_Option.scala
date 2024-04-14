package com.yqj.scala.base.class09

object Demo02_Option {

  // 使用Option类型封装返回结果
  def divide(a: Int, b: Int) = {
    if (b == 0) { // 除数为0
      None
    } else {
      Some(a / b) // Some包裹正常结果
    }
  }

  def main(args: Array[String]): Unit = {
    // 方式1，普通实现
    val res1 = divide(10, 0)
    println(s"res1: ${res1}")
    // 方式2，模式匹配
    res1 match {
      case Some(x) => println(s"res1: ${res1}")
      case None => println("error")
    }
    // 方式3，getOrElse
    println(res1.getOrElse("error"))
  }
}
