package com.yqj.scala.base.class11

object Demo07_TTrait {

  trait Logger[T] {
    val info: T

    def show(msg: T)
  }

  // 定义单例对象继承Logger特质，指定泛型类型
  object ConsoleLogger extends Logger[String] {
    override val info: String = "logger"

    override def show(msg: String): Unit = println(msg)
  }

  def main(args: Array[String]): Unit = {
    // 泛型特质
    println(ConsoleLogger.info)
    ConsoleLogger.show("hello world")
  }
}
