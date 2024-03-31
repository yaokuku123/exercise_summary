package com.yqj.scala.base.class04

object Demo02_ClassMember {

  // 定义Person类
  class Person {
    // 定义成员变量
    // 方式一：普通写法
    //    val name: String = ""
    // 方式二：采用类型推断
    //    val name = "" // val定义无法修改
    //    var name = ""
    // 方式三：采用下划线初始化成员变量
    // String => null
    // Int => 0
    // Boolean => false
    // Double => 0.0
    //    val name:String = _ // error，采用下划线初始化变量，只针对var类型的变量有效
    var name: String = _ // 必须有类型，否则下划线无法类型推断
    var age = 0

    // 定义成员方法
    def printMsg(msg: String) = {
      println(msg)
    }
  }

  def main(args: Array[String]): Unit = {
    // 定义和访问成员变量
    // 空参构造，可以省略()
    val p = new Person
    // 成员变量赋值
    p.name = "yorick"
    p.age = 23
    // 打印成员变量
    println(p.name, p.age)
    // 调用成员方法
    p.printMsg("hello class")
  }
}
