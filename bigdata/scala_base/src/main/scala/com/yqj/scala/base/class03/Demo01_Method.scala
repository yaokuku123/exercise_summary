package com.yqj.scala.base.class03


object Demo01_Method {
  // 1.参数列表的参数类型不能省略
  // 2.返回值类型可以省略，scala自动推断
  // 3.返回值可以不写return，默认就是块表达式{}的值

  // 1.方法使用
  // 方法定义-完整版
  def getMax(a: Int, b: Int): Int = {
    return if (a >= b) a else b
  }

  // 方法定义-简化版
  def getMax2(a: Int, b: Int) = if (a >= b) a else b

  // 2.递归方法
  // 定义递归方法必须定义返回值的类型
  def factorial(n: Int): Int = {
    if (n == 1) 1 else n * factorial(n - 1)
  }

  def main(args: Array[String]): Unit = {
    println(getMax(10, 20))
    println(getMax2(10, 20))

    println(factorial(5))

    // 3.惰性方法
    // 声明lazy，方法执行被推迟，直到首次使用该值时，方法才被执行
    // 场景：
    // 1.打开数据库连接；2.提升特定模块的启动时间；3.确保对象某些字段优先初始化
    lazy val num = getMax(2, 3)
    println(num) // 调用时方法才执行
  }
}
