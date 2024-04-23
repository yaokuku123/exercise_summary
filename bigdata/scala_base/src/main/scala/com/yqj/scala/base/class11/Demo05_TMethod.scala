package com.yqj.scala.base.class11

object Demo05_TMethod {
  def getMiddleElement[T](arr: Array[T]): T = arr(arr.length / 2)

  def main(args: Array[String]): Unit = {
    // 泛型方法
    // 把泛型定义在方法声明上，即该方法的参数类型由泛型决定，在调用是明确具体的数据类型
    println(getMiddleElement(Array(1, 2, 3)))
    println(getMiddleElement(Array("a", "b", "c")))
    println("-" * 10)
  }
}
