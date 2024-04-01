package com.yqj.scala.base.class04

object Demo07_ClassPartner {

  // 定义伴生类
  class Generals {
    def toWar() = println(s"拿着${Generals.armsName}武器，上阵杀敌")
  }

  // 定义伴生对象
  object Generals {
    private val armsName = "AK47"
  }

  def main(args: Array[String]): Unit = {
    // 伴生对象
    // 一个class和object同名，这个object称为伴生对象，这个class称为伴生类
    // 1.伴生对象必须和伴生类同名
    // 2.伴生对象和伴生类必须在同一个scala源文件中
    // 3.伴生对象和伴生类可以互相访问private属性
    val generals = new Generals
    generals.toWar()
  }
}
