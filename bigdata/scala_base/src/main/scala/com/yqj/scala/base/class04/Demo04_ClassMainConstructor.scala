package com.yqj.scala.base.class04

object Demo04_ClassMainConstructor {

  class Person(val name: String = "yorick", val age: Int = 23) {
    println("主构造器代码")
  }

  def main(args: Array[String]): Unit = {
    // 主构造器的参数列表直接定义在类名后面，添加val/var表示直接通过主构造器定义成员变量
    // 构造器参数可以指定默认值
    // 创建实例，可以指定字段进行初始化
    // 整个class除了字段定义和方法定义的代码都是构造代码

    // 创建空对象
    val p1 = new Person
    println(s"name:${p1.name},age:${p1.age}")
    // 创建全参对象
    val p2 = new Person("tom", 12)
    println(s"name:${p2.name},age:${p2.age}")
    // 创建指定参数对象
    val p3 = new Person(age = 30)
    println(s"name:${p3.name},age:${p3.age}")
  }
}
