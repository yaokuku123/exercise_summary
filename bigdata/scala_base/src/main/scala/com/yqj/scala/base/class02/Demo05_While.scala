package com.yqj.scala.base.class02

object Demo05_While {
  def main(args: Array[String]): Unit = {
    // while表达式

    // 1.while普通使用
    var i = 1
    while (i <= 10) {
      println(i)
      i += 1 // error i++
    }
    println("-" * 20)

    // 2.do while普通使用
    var j = 1
    do {
      println(j)
      j += 1
    } while (j <= 10)


  }
}
