package com.yqj.scala.base.class08

import scala.collection.mutable.ArrayBuffer

object Demo01_ArrayOpt {

  def main(args: Array[String]): Unit = {
    // 一、定长数组的定义
    // 1.定义一个长度为10的整型数组，设置第一个元素为11，并打印第一个元素
    val arr1 = new Array[Int](10)
    arr1(0) = 11
    println(arr1(0))
    // 2.定义包含三个字符串的数组
    val arr2 = Array("Java", "Scala", "Python")
    println(arr2.length)
    println(arr2.size)

    // 二、变长数组的定义
    val mutableArr1 = ArrayBuffer[Int]()
    val mutableArr2 = ArrayBuffer("Java", "Scala", "Python")
    println(mutableArr1)
    println(mutableArr2)

    // 三、变长数组的增删改
    // 增加减少单个元素
    mutableArr2 += "hadoop"
    mutableArr2 -= "Java"
    // 增加减少数组中的多个元素（可以是定长也可以是变长数组）
    mutableArr2 ++= Array("Spark", "Flink")
    mutableArr2 --= ArrayBuffer("Scala", "Python")
    println(mutableArr2)

    // 四、数组的遍历
    val arr = Array(4, 1, 6, 5, 2, 3)
    for (i <- 0 to arr.length - 1) println(arr(i))
    println("-" * 10)
    for (i <- 0 until arr.length) println(arr(i))
    println("-" * 10)
    for (i <- arr) println(i)

    // 五、数组的常用算法
    println(s"sum: ${arr.sum}")
    println(s"max: ${arr.max}")
    println(s"min: ${arr.min}")
    // 排序，默认升序，返回新数组
    val arrSorted = arr.sorted
    // 反转，返回新数组
    val arrReverse = arr.reverse
    for (i <- arrSorted) println(i)
    println("-" * 10)
    for (i <- arrReverse) println(i)
  }
}
