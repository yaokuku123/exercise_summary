package com.yqj.scala.base.class09

object Demo03_PartialFunction {
  def main(args: Array[String]): Unit = {
    // 偏函数
    // 说明：偏函数是指花括号内没有match的一组case语句，是PartialFunction[A,B]类型的实例对象，A代表输入参数类型，B代表输出参数类型
    // 定义偏函数
    val pf: PartialFunction[Int, String] = {
      case 1 => "one"
      case 2 => "two"
      case 3 => "three"
      case _ => "other"
    }
    println(pf(2))
    println("-" * 10)

    // 偏函数结合map函数
    val list = (1 to 10).toList
    // 偏函数
    list.map {
      case x if x >= 1 && x <= 3 => "[1-3]"
      case x if x >= 4 && x <= 8 => "[4-8]"
      case _ => "(8-*]"
    }.foreach(println(_))
  }
}
