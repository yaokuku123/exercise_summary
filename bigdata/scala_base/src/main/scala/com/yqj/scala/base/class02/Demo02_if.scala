package com.yqj.scala.base.class02

object Demo02_if {
  def main(args: Array[String]): Unit = {
    // 1.scala中大括号内的逻辑代码如果只有一行，可以省略
    // 2.scala条件表达式也有返回值
    // 3.scala没有三元表达式，可以使用if代替
    val gender = "male"
    val result = if (gender == "male") 1 else 0
    println("result:" + result)
  }
}
