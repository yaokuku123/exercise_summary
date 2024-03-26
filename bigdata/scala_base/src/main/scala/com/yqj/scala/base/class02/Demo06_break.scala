package com.yqj.scala.base.class02

import scala.util.control.Breaks._

object Demo06_break {
  def main(args: Array[String]): Unit = {
    // break continue实现

    // 1.导包 import scala.util.control.Breaks._
    // 2.使用breakable将for表达式包裹 or 使用breakable将for的循环体包裹
    // 3.退出的位置调用 break()

    // break
    breakable {
      for (i <- 1 to 10) {
        if (i == 5) break() else println(i)
      }
    }
    println("-" * 20)
    // continue
    for (i <- 1 to 10) {
      breakable {
        if (i % 3 == 0) break() else println(i)
      }
    }
  }
}
