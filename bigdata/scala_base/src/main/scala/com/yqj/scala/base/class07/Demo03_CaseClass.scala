package com.yqj.scala.base.class07

object Demo03_CaseClass {

  // 定义样例类
  // 默认val，可以省略不写
  case class Person(name:String = "yorick",var age:Int = 23) {}

  def main(args: Array[String]): Unit = {
    // 样例类

    // 创建对象
    val p = new Person
    println(s"before: ${p.name}, ${p.age}")

    // 修改属性
//    p.name = "tom" // error, val类型的属性无法修改
    p.age = 30
    println(s"after: ${p.name}, ${p.age}")
  }
}
