package com.yqj.scala.base.class08

object Demo06_MapOpt {
  def main(args: Array[String]): Unit = {
    // map的相关操作
    // 创建不可变Map
    // 方法1：使用箭头的方式创建Map
    val map1 = Map("yorick" -> 23, "tom" -> 24, "tom" -> 40)
    // 方法2：使用小括号的方式创建Map，注意：key相同的时候，后面的数据会覆盖前面的数据
    val map2 = Map(("yorick", 23), ("tom", 40), ("tom", 24))
    // map1("yorick") = 11 error 不可以修改不可变Map的长度和数值
    println(map1)
    println(map2)
    println("-" * 10)

    // 创建可变Map
    import scala.collection.mutable.Map
    val map3 = Map("yorick" -> 23, "tom" -> 24)
    map3("yorick") = 10 // 可以修改数值
    println(map3)
  }
}
