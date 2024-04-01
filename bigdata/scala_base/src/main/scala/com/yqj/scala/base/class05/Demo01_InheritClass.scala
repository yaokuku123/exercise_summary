package com.yqj.scala.base.class05

object Demo01_InheritClass {

  class Person {
    var name = ""
    var age = 0

    def eat() = println("人要吃饭")
  }

  class Student extends Person {}

  class Teacher extends Person {}


  def main(args: Array[String]): Unit = {
    // 类的继承
    // 1.scala中使用关键字extends表示继承
    // 2.可在子类中定义父类没有的属性和方法，或者重写父类的方法
    // 3.类和单例对象都可以有父类
    val student = new Student
    student.name = "yorick"
    student.age = 23
    println(student.name, student.age)

    val teacher = new Teacher
    teacher.name = "tom"
    teacher.age = 42
    println(teacher.name, teacher.age)
  }

}
