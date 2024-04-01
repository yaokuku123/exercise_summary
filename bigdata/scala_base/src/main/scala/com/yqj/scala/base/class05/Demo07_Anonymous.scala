package com.yqj.scala.base.class05

object Demo07_Anonymous {

  abstract class Person {
    def sayHello(): Unit
  }

  def show(p: Person): Unit = {
    p.sayHello()
  }

  def main(args: Array[String]): Unit = {
    // 匿名内部类

    // 当对象方法仅调用一次时，可以使用匿名内部类的方式
    // 注：如果类的主构造器参数列表为空，则小括号可以省略不写
    new Person {
      override def sayHello(): Unit = println("hello scala")
    }.sayHello()

    // 可以作为方法的参数进行传递
    show(new Person {
      override def sayHello(): Unit = println("hello show method")
    })
  }
}
