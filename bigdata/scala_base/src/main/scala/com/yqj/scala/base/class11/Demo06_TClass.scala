package com.yqj.scala.base.class11

object Demo06_TClass {

  // 定义Pair泛型对象，包含两个字段，且两个字段的类型不固定
  class Pair[T](var a: T, var b: T)

  def main(args: Array[String]): Unit = {
    // 泛型对象
    // 创建对象1，对象中的字段为Int类型
    val p1 = new Pair[Int](1, 2)
    println(p1.a, p1.b)
    // 创建对象2，对象中的字段为String类型
    val p2 = new Pair[String]("a", "b")
    println(p2.a, p2.b)
  }
}
