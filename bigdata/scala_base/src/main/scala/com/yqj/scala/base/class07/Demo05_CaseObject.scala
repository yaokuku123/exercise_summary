package com.yqj.scala.base.class07

object Demo05_CaseObject {

  // 定义特质
  trait Gender

  // 定义样例对象继承Gender特质
  case object Male extends Gender

  case object Female extends Gender

  // 定义样例类，属性使用定义的样例对象作为类型
  case class Person(var name: String, var gender: Gender) {}

  def main(args: Array[String]): Unit = {
    // 样例对象
    val p = Person("yorick", Male)
    println(p)
  }
}
