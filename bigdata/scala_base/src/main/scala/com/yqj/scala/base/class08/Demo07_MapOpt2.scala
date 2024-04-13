package com.yqj.scala.base.class08

import scala.collection.mutable

object Demo07_MapOpt2 {
  def main(args: Array[String]): Unit = {
    // Map的相关方法
    // 创建Map
    val map1 = mutable.Map("yorick" -> 23, "tom" -> 25)
    // 获取yorick年龄
    println(map1("yorick"))
    // 获取全部key
    println(map1.keys)
    // 获取全部value
    println(map1.values)
    // 打印Map
    for ((k, v) <- map1) println(k, v)
    // 获取不到打印默认值
    println(map1.getOrElse("yorick2", -1))
    // 向可变map中添加/减少元素
    map1 += "jerry" -> 12
    map1 -= "tom"
    println(map1)
  }
}
