package com.yqj.scala.base.class09

object Demo05_Exception {
  def main(args: Array[String]): Unit = {
    // 异常处理
    try {
      val res = 10 / 0
    } catch {
      case ex: ArithmeticException => println("ArithmeticException")
      case ex: NullPointerException => println("NullPointerException")
      case ex: Exception => ex.printStackTrace()
    } finally {
      println("finally code...")
    }
    println("running...")
    throw new Exception("i am a exception")
  }
}
