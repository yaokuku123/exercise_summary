package com.yqj.scala

import java.util.Date

object Function_03 {
  def main(args: Array[String]): Unit = {
    println("-------1.基础使用----------")

    // 函数直接{}，表示该函数返回的为Unit类型
    def func01() {
      println("func01")
    }

    func01()

    // 不加return关键字，则会类型推断，加return则需要指明返回类型
    def func02_1() = {
      "hello01"
    }

    def func02_2(): String = {
      return "hello02"
    }

    println(func02_1())
    println(func02_2())

    println("-------2.递归函数----------")

    // 递归函数调用自身，无法自动类型推断返回的类型，需要手动指定
    def func03(num: Int): Int = {
      if (num == 1) { // 递归终止条件
        num
      } else {
        num * func03(num - 1)
      }
    }

    println(func03(4))

    println("-------3.默认值函数----------")

    def func04(a: String = "yorick", b: Int = 25): Unit = {
      println(s"a:$a, b:$b")
    }

    func04()
    func04("jerry", 30)
    func04("tom")
    func04(b = 40)

    println("-------4.匿名函数----------")
    //函数：
    // 1 签名：(Int,Int)=>Int ：  （参数类型列表）=> 返回值类型
    // 2 匿名函数： (a:Int,b:Int) => { a+b }  ：（参数实现列表）=> 函数体
    val func05: (Int, Int) => Int = (a: Int, b: Int) => {
      a + b
    }
    println(func05(10, 20))

    println("--------5.嵌套函数---------")

    def func06_outer(name: String): Unit = {
      def func06_inner(): Unit = {
        println("name: " + name) // 内层函数可以共享到外层函数的数据
      }

      func06_inner()
    }

    func06_outer("hello world")

    println("--------6.偏应用函数---------")

    def func07(date: Date, level: String, msg: String): Unit = {
      println(s"date:$date, level:$level, msg:$msg")
    }

    func07(new Date(), "info", "ok")
    val info = func07(_: Date, "info", _: String) // 偏应用函数，理解：应用到info情况的场景
    val error = func07(_: Date, "error", _: String) // 偏应用函数，理解：应用到error情况的场景
    info(new Date(), "info test")
    error(new Date(), "error test")

    println("--------7.可变参数---------")

    def func08(nums: Int*): Unit = {
      for (elem <- nums) {
        print(elem + " ")
      }
      println()
      nums.foreach((num: Int) => {
        print(num + " ")
      })
      println()
    }

    func08(1)
    func08(10, 11, 12)

    println("--------8.高阶函数---------")

    //函数可作为参数，函数也可作为返回值
    def func09_calc(num1: Int, num2: Int, func: (Int, Int) => Int): Unit = {
      println(func(num1, num2))
    }

    def func09_calcFactory(ops: String): (Int, Int) => Int = {
      if ("+".equals(ops)) {
        (num1: Int, num2: Int) => num1 + num2
      } else if ("-".equals(ops)) {
        (num1: Int, num2: Int) => num1 - num2
      } else if ("*".equals(ops)) {
        (num1: Int, num2: Int) => num1 * num2
      } else if ("/".equals(ops)) {
        (num1: Int, num2: Int) => num1 / num2
      } else {
        (num1: Int, num2: Int) => 0
      }
    }

    func09_calc(10, 20, func09_calcFactory("-"))

    println("--------9.柯里化---------")

    def func10(nums: Int*)(strs: String*): Unit = {
      nums.foreach(println)
      strs.foreach(println)
    }

    func10(1, 2, 3)("yorick", "tom", "jerry")

    println("--------10.方法---------")
    //方法不想执行，赋值给一个引用  方法名+空格+下划线
    val method = func11 _ // 赋值方法引用，不执行
    method() // 执行方法
  }

  def func11(): Unit = {
    println("hello world method")
  }
}
