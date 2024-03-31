package com.yqj.scala.base.class04

object Demo05_ClassOtherConstructor {

  class Person(val name: String, val address: String) {
    // 定义辅助构造器
    def this(arr: Array[String]) {
      // 辅助构造器第一行代码必须访问主构造器或者其他的辅助构造器
      this(arr(0), arr(1))
    }
  }

  def main(args: Array[String]): Unit = {
    // 辅助构造器
    val p = new Person(Array("yorick", "beijing"))
    println(p.name, p.address)
    // 主构造器
    val p2 = new Person("tom", "shanghai")
    println(p2.name, p2.address)
  }
}
