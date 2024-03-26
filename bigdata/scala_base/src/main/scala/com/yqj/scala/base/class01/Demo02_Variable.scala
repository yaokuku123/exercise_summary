package com.yqj.scala.base.class01

object Demo02_Variable {
  def main(args: Array[String]): Unit = {
    // 变量

    // 语法格式：val/var 变量名:变量类型 = 初始值
    // val定义的不可重新赋值；var定义的可重新赋值
    val name:String = "yorick"
//    name = "tim"  // error
    println(name)

    var age:Int = 32
    age = 23
    println(age)

    // 类型推断
    val address = "beijing"
    println(address)
  }
}
