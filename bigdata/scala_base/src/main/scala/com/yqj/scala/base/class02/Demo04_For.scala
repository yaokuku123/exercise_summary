package com.yqj.scala.base.class02

object Demo04_For {
  def main(args: Array[String]): Unit = {
    // for 循环

    // 1.for循环使用
    for (i <- 1 to 10) {
      println("hello world " + i)
    }
    println("-" * 20)

    // 2.for循环嵌套
    // 普通写法
    for (i <- 1 to 3) {
      for (j <- 1 to 5) {
        print("*")
      }
      println()
    }
    println("-" * 20)
    // 压缩版
    for (i <- 1 to 3) {
      for (j <- 1 to 5) {
        if (j == 5) println("*") else print("*")
      }
    }
    println("-" * 20)
    // 合并版
    for (i <- 1 to 3; j <- 1 to 5) {
      if (j == 5) println("*") else print("*")
    }
    println("-" * 20)
    // 3.for循环守卫
    // 只输出1～10中能被3整除的数
    for (i <- 1 to 10 if i % 3 == 0) {
      println(i)
    }
    println("-" * 20)

    // 4.for推导式
    // for循环也是有返回值，在for循环体中，可以使用yield构建出一个集合
    val nums = for (i <- 1 to 10) yield i * 10
    println(nums)
  }
}
