package com.yqj.scala.base.class02

object Demo01_StringEq {
  def main(args: Array[String]): Unit = {
    // 比较两个字符串的值/地址
    val str1: String = "abc"
    val str2: String = str1 + ""
    // 比较两个字符串的值是否值相等
    println(str1 == str2) // true
    // 比较两个字符串的地址是否相等
    println(str1.eq(str2)) // false
  }
}
