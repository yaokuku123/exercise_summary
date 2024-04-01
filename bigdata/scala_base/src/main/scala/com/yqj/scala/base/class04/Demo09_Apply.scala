package com.yqj.scala.base.class04

object Demo09_Apply {

  class Person(var name: String = "", var age: Int = 0) {}

  // 定义伴生对象
  object Person {
    // apply()方法，用来实现创建Person对象的时候免new
    // 该方法会由Scala SDK自动调用
    def apply(name: String, age: Int): Person = new Person(name, age)
  }

  def main(args: Array[String]): Unit = {
    // apply方法
    val p = Person("yorick", 23)
    println(p.name, p.age)
  }
}
