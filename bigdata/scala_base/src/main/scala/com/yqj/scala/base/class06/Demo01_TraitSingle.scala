package com.yqj.scala.base.class06

object Demo01_TraitSingle {

  // 定义特质
  // 继承关系也是使用extends
  // 多个特质 用 with间隔
  trait Logger {
    def log(msg: String): Unit
  }

  // 定义类继承特质
  class ConsoleLogger extends Logger {
    override def log(msg: String): Unit = println(msg)
  }

  def main(args: Array[String]): Unit = {
    // 类继承单个特质

    // 1.特质可以提高代码的复用性
    // 2.特质可以提高代码的扩展性和可维护性
    // 3.类与特质之间是继承关系，只不过类与类之间只支持单继承，类与特质之间支持多继承
    // 4.scala特质中可以有普通字段，抽象字段，普通方法，抽象方法
    // 只有抽象内容，叫瘦接口；既有抽象又有具体内容，叫富接口
    val cl = new ConsoleLogger
    cl.log("hello trait")
  }
}
