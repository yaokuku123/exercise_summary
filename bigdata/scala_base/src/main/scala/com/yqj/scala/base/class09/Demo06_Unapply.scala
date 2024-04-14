package com.yqj.scala.base.class09

object Demo06_Unapply {

  class Student(var name: String, var age: Int)

  object Student {
    def apply(name: String, age: Int): Student = new Student(name, age)

    def unapply(s: Student) = {
      if (s == null) {
        None
      } else {
        Some(s.name, s.age)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    // 对象属性提取器
    // 创建对象
    val student = Student("yorick", 23)
    student match {
      case Student(name, age) => println(s"${name},${age}")
      case _ => println("not match")
    }
  }
}
