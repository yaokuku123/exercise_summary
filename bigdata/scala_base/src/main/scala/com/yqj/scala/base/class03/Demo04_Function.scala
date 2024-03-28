package com.yqj.scala.base.class03

object Demo04_Function {

  def add(a: Int, b: Int) = a + b

  def main(args: Array[String]): Unit = {
    // 函数
    // 1.在scala中，函数是一个对象
    // 2.类似于方法，函数也有参数列表和返回值
    // 3.函数定义不需要使用def
    // 4.无需指定返回值的类型
    // 只有一行，花括号可以省略
    val getSum = (a: Int, b: Int) => a + b
    println(getSum(1, 2))

    // 方法与函数的区别
    // 方法属于类或对象，运行时加载到JVM方法区
    // 可以将函数赋值给一个变量，运行时加载到JVM堆内存
    // 函数是对象，而方法隶属于对象，可以理解为方法归属于函数

    // 方法赋值给变量
    val mf = add _
    println(mf(2, 3))
  }
}
