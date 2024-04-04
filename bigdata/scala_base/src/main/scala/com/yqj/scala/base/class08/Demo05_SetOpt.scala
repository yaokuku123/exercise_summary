package com.yqj.scala.base.class08

import scala.collection.mutable


object Demo05_SetOpt {
  def main(args: Array[String]): Unit = {
    // 一、不可变集创建
    // 1.定义空的不可变集
    val set1 = Set[Int]()
    // 2.定义有初始值的不可变集
    val set2 = Set(1, 1, 3, 2, 4, 8)
    println(set1)
    println(set2)
    println("-" * 10)

    // 二、不可变集常用操作
    val s1 = Set(1, 1, 2, 3, 4, 5)
    println(s"size: ${s1.size}")
    // 遍历集
    for (i <- s1) println(i)
    // 删除元素1
    val s2 = s1 - 1
    println(s"s2:${s2}")
    // 拼接另一个集
    val s3 = s1 ++ Set(6, 7, 8)
    println(s"s3:${s3}")
    // 拼接另一个列表
    val s4 = s1 ++ List(6, 7, 8, 9)
    println(s"s4:${s4}")
    println("-" * 10)

    // 三、可变集操作
    val mutableSet = mutable.Set(1, 2, 3, 4)
    // 添加元素
    mutableSet += 5
    // 添加多个元素
    mutableSet ++= List(6, 7, 8) // or Set()
    // 删除元素
    mutableSet -= 1
    // 删除多个元素
    mutableSet --= mutable.Set(2, 3) // or List()
    println(mutableSet)
  }
}
