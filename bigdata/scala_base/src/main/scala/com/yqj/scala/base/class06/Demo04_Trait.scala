package com.yqj.scala.base.class06

object Demo04_Trait {
  trait Hero {
    // 具体字段
    var name = "yorick"
    // 抽象字段
    var arms:String // 说明：只有抽象var变量才可以覆写，已经赋值的var变量无法覆写
    // 具体方法
    def eat():Unit = println(s"${name} like eat")
    // 抽象方法
    def toWar():Unit
  }

  class Generals extends Hero {
    override var arms: String = "AK47"

    override def toWar(): Unit = println(s"${name} use ${arms} to war")
  }

  def main(args: Array[String]): Unit = {
    // trait成员
    val generals = new Generals
    generals.eat()
    generals.toWar()
  }
}
