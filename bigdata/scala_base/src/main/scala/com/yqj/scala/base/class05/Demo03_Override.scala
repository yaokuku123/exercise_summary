package com.yqj.scala.base.class05

object Demo03_Override {

  class Person {
    val name = "yorick"
    var age = 23

    def sayHello() = println("hello Person")
  }

  class Student extends Person {
    override val name: String = "sub_yorick"
    // error 不同重写var修饰的变量
    //    override var age = 25

    override def sayHello(): Unit = {
      // 调用父类的成员方法
      super.sayHello()
      println("hello Student")
    }
  }

  def main(args: Array[String]): Unit = {
    // 方法重写
    val student = new Student
    println(student.name, student.age)
    student.sayHello()
  }
}
