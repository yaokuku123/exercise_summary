package com.yqj.scala

object If_While_For_02 {
  def main(args: Array[String]): Unit = {
    println("-------------IF--------------")
    val score = 87
    if (score >= 90) {
      println("nice")
    } else if (score >= 60 && score < 90 ) {
      println("so so")
    } else {
      println("bad")
    }

    println("-------------WHILE--------------")
    var index = 0
    while (index <= 10) {
      if (index != 10) {
        print(index + " ")
      } else {
        println(index)
      }
      index += 1
    }

    println("-------------FOR--------------")
    // t0 包括最后一个数据
    val seqs01 = 1 to 10
    for (i <- seqs01) {
      print(i + " ")
    }
    println()

    // 1.循环逻辑，业务逻辑相分离的设计
    // 2.until 不包括最后一个数据
    // 3.if 哨兵可以做额外的循环逻辑判别
    for (i <- 1 until 10 if i % 2 == 1) {
      print(i + " ")
    }
    println()

    // 双层for循环，实现99乘法表
    // 两层for循环可以写到一起，并且支持哨兵循环判别逻辑
    for (i <- 1 to 9; j <- 1 to 9 if j <= i) {
      if (j <= i) print(s"$i * $j = ${i * j}\t")
      if (j == i) println()
    }

  }
}
