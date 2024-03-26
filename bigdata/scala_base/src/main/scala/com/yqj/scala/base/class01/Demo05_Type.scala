package com.yqj.scala.base.class01

object Demo05_Type {
  def main(args: Array[String]): Unit = {
    // 数据类型
    // Scala是强类型语言
    // Any 所有类型的父类，有两个子类AnyVal和AnyRef
    // AnyVal 所有数值类型的父类
    // AnyRef 所有引用类型的父类
    // Unit 表示空，Unit是AnyVal的子类，只有一个实例，类似Java的void
    // Null是AnyRef的子类，也是所有引用类型的子类，实例为null，可以将null赋值给任意引用类型
    // Nothing是所有类型的子类，结合异常使用

    // Null类型不能转换为Int类型，不是Int类型的子类
    //    val a: Int = null // error
    //    println(a)
  }
}
