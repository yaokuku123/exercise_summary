package com.yqj.scala.base.class12

import scala.collection.{SortedSet, mutable}

object Demo06_SetOpt {
  def main(args: Array[String]): Unit = {
    // 创建SortedSet集合
    val s1 = SortedSet(1, 4, 3, 2, 5)
    // 创建HashSet集合
    val s2 = mutable.HashSet(1, 4, 3, 2, 5)
    // 创建LinkedHashSet集合
    val s3 = mutable.LinkedHashSet(1, 4, 3, 2, 5)
    println(s1) // TreeSet(1, 2, 3, 4, 5) 唯一，排序，默认降序
    println(s2) // Set(1, 5, 2, 3, 4) 唯一，无序
    println(s3) // Set(1, 4, 3, 2, 5) 唯一，有序
  }
}
