package com.yqj.scala

// Scala中的object约等于Java的static，实质上是采用的单例设计
object Object_Class_01 {

  println("object main start")
  def main(args: Array[String]): Unit = {
    println("object main")
    val person = new Person("yorick", 26)
    person.printMsg()

  }
  println("object main end")
}

// 1.有默认构造，类里裸露的代码均放入默认构造中
// 2.类名构造器中的参数就是类的成员属性，且默认是val类型，且默认是private
// 3.只有在类名构造其中的参数可以设置成var，其他方法函数中的参数都是val类型的，且不允许设置成var类型
class Person(var name:String) {
  var age = 10

  // 自定义构造
  def this(_name:String,_age:Int) {
    // 必须调用默认构造
    this(_name)
    age = _age
  }

  println("person start")
  def printMsg(): Unit = {
    println(s"person printMsg: name: $name, age: $age, address: ${Person.address}")
    // 使用伴生object中的私有方法
    Person.sayHello()
  }
  println("person end")
}

// Person类的伴生object，可在伴生class中访问伴生object中的私有属性和方法
object Person {
  private val address = "bj"

  private def sayHello(): Unit = {
    println("person say hello")
  }
}

