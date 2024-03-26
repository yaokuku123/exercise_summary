package com.yqj.scala.base.class01

import scala.io.StdIn

object Demo07_StdIn {
  def main(args: Array[String]): Unit = {
    // 键盘录入

    // 用户录入字符串
    println("please enter a str:")
    println("your enter is:" + StdIn.readLine())

    // 用户录入整数
    println("please eneter a int number:")
    println("your enter is:" + StdIn.readInt())
  }
}
