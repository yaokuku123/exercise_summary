package com.yqj.scala.base.class01

import scala.io.StdIn

object Product01_SayHello {
  def main(args: Array[String]): Unit = {
    // 案例：打招呼
    println("请输入姓名：")
    val name = StdIn.readLine()
    println("请输入年龄：")
    val age = StdIn.readInt()
    println(s"我的名字是：${name},今年${age}岁")
  }
}
