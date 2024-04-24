package com.yqj.scala.base.class12

object Demo07_MapOpt {
  def main(args: Array[String]): Unit = {
    // 定义Map
    val map = Map("a" -> 1, "b" -> 2, "c" -> 3)
    // 遍历Map
    for ((k, v) <- map) println(k, v)
    map.foreach(println(_))
    // filterKeys过滤
    println(map.filterKeys(_ == "b"))
  }
}
