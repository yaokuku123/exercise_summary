package com.yqj.scala.base.class05

object Demo04_IsInstanceOf {

  class Person {}

  class Student extends Person {
    def sayHello() = println("hello student")
  }

  def main(args: Array[String]): Unit = {
    // isInstanceOf/asInstanceOf
    // 多态，父类引用指向子类对象
    val p: Person = new Student()
    // 判断对象p是否为Student类型的对象
    if (p.isInstanceOf[Student]) {
      // 转换为Student类型的对象，并调用sayHello方法
      val s = p.asInstanceOf[Student]
      s.sayHello()
    }
  }
}
