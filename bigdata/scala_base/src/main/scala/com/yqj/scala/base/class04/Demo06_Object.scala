package com.yqj.scala.base.class04

object Demo06_Object {

  // 定义单例对象
  object Dog {
    // 定义变量
    val legNum = 4
    // 定义方法
    def printDogInfo() = println("this is my dog")
  }

  def main(args: Array[String]): Unit = {
    // 调用单例对象的变量
    println(Dog.legNum)
    // 调用单例对象的方法
    Dog.printDogInfo()
  }
}
