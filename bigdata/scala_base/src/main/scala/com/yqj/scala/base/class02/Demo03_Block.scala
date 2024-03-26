package com.yqj.scala.base.class02

object Demo03_Block {
  def main(args: Array[String]): Unit = {
    // 块表达式

    // 1.{}表示一个块表达式
    // 2.和if表达式一样，块表达式可以有值
    // 3.值就是最后一个表达式的值
    val a = {
      println("hello world")
      2 * 3
    }
    println(s"a:${a}")
  }

}
