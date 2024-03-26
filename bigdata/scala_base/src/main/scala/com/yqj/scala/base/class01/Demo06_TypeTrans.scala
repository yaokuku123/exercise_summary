package com.yqj.scala.base.class01

object Demo06_TypeTrans {
  def main(args: Array[String]): Unit = {
    // 类型转换
    // 值类型的类型转换 和 引用类型的类型转换

    // 值类型的类型转换：自动转换，强制转换
    // 自动转换
    val a: Int = 10
    val b: Double = a + 2.21
    println(b)

    // 强制转换
    val c: Int = (a + 2.21).toInt
    println(c)

    // 值类型 <=> 字符串类型
    // 值 => 字符串
    val s1: String = a + ""
    val s2: String = a.toString
    println(a + 10, s1 + 10, s2 + 10)

    // 字符串 => 值
    val ss1: String = "10"
    val ss2: String = "1.23"
    val ss3: String = "false"
    println(ss1.toInt, ss2.toDouble, ss3.toBoolean)
  }
}
