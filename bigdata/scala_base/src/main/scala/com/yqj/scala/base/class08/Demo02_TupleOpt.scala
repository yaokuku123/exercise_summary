package com.yqj.scala.base.class08

object Demo02_TupleOpt {
  def main(args: Array[String]): Unit = {
    // 一、tuple创建
    // 方式1 通过小括号实现
    val tuple1 = ("yorick", 23)
    // 方式2 通过箭头实现（只有两个元素的情况）
    val tuple2 = "tom" -> 25
    println(tuple1)
    println(tuple2)

    // 二、元素的访问
    // 1.使用_编号获取
    println(s"name:${tuple1._1},age:${tuple1._2}")
    // 2.通过迭代器遍历
    val it = tuple1.productIterator
    for (i <- it) println(i)
  }
}
