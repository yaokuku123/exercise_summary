package com.yqj.scala.base.class04

object Demo01_ClassCreate {

  class Person() {}

  // 如果类是空的没有任何成员，可以省略{}
  // 如果构造器参数是空的，可以省略()
  class Person2

  def main(args: Array[String]): Unit = {
    // 创建对象
    val p = new Person()
    println(p)

    // 如果构造器参数是空的，可以省略()
    val p2 = new Person2
    println(p2)
  }
}
