package com.yqj.scala.base.class11

object Demo01_HighFunction {

  def main(args: Array[String]): Unit = {
    // 高阶函数

    // 作为值的函数
    val l1 = (1 to 10).toList
    val func = (x: Int) => {
      "*" * x
    }
    val l11 = l1.map(func)
    println(l11)
    println("-" * 10)

    // 匿名函数
    val l2 = (1 to 10).toList
    //    val l22 = l2.map((x:Int) => {"*" * x })
    // 简化
    val l22 = l2.map("*" * _)
    println(l22)
    println("-" * 10)
  }
}
