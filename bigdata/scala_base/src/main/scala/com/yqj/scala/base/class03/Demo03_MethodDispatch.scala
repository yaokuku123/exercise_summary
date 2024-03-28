package com.yqj.scala.base.class03

object Demo03_MethodDispatch {

  def sayHello() = println("hello world")

  def sayHello2() {println("hello world2")}

  def main(args: Array[String]): Unit = {
    // scala中方法的调用方式

    // 后缀调用法
    println(Math.abs(-10))
    println(1.+(2))
    println(1.to(10))

    // 中缀调用法
    println(Math abs -10)
    println(1 + 2)
    println(1 to 10)

    // 花括号调用法
    // 方法只能有一个参数，才能用该方法，默认会把最后的值作为方法的参数
    println(Math.abs{
      println("hello world")
      -20
    })

    // 无括号调用法
    // 如果方法没有参数，则可以不写括号
    // 如果方法的返回值是Unit，称之为过程，方法可以省略=
    sayHello
    sayHello2
  }
}
