package com.yqj.scala.base.class11

import java.io.File

import scala.io.Source

// 手动导入
object Demo03_Implicit {

  class RichFile(file: File) {
    def read() = Source.fromFile(file).mkString
  }

  object ImplicitDemo {
    implicit def file2RichFile(file: File) = new RichFile(file)
  }

  def main(args: Array[String]): Unit = {
    // 隐式转换 手动导入
    // 执行流程：
    // 1.先找File类是否有read方法，有就用
    // 2.没有read方法，就去查看是否有该类型的隐式转换，
    //    若有，则转为其他类型的对象；若无，则报错
    // 3.转为其他类型对象，查看该对象是否有read方法
    //    若有，则调用；若无，则报错
    import ImplicitDemo.file2RichFile
    val file = new File("./scala_base/data/1.txt")
    println(file.read())
  }
}

// 自动导入
object Demo03_ImplicitAuto {

  class RichFile(file: File) {
    def read() = Source.fromFile(file).mkString
  }


  def main(args: Array[String]): Unit = {
    // 隐式转换 自动导入
    // 如果在当前作用域中有隐式转换方法，则会自动导入
    // 将隐式转换方法定义在main方法中
    implicit def file2RichFile(file: File) = new RichFile(file)

    val file = new File("./scala_base/data/2.txt")
    println(file.read())
  }
}
