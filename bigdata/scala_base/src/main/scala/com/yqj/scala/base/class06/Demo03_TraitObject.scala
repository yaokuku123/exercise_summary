package com.yqj.scala.base.class06

object Demo03_TraitObject {

  trait Info {
    def info(msg: String): Unit
  }

  trait Warning {
    def warning(msg: String): Unit
  }

  // 单例对象混入特质
  object ConsoleLogger extends Info with Warning {
    override def info(msg: String): Unit = println(s"info msg:${msg} ")

    override def warning(msg: String): Unit = println(s"warning msg: ${msg}")
  }

  def main(args: Array[String]): Unit = {
    // 单例对象继承特质
    ConsoleLogger.info("hello world")
    ConsoleLogger.warning("make some problem")
  }
}
