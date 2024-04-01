package com.yqj.scala.base.class05

object Demo02_InheritObject {

  class Person {
    var name = ""

    def sayHello(): Unit = println("hello scala")
  }

  object Student extends Person {}

  def main(args: Array[String]): Unit = {
    // 单例对象的继承
    // scala中单例对象也可以继承类

    // 赋值并打印单例对象中的属性和方法
    Student.name = "yorick"
    println(Student.name)
    Student.sayHello()
  }
}
