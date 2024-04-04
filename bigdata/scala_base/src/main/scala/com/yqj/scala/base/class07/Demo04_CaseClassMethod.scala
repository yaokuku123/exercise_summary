package com.yqj.scala.base.class07

object Demo04_CaseClassMethod {

  case class Person(var name: String, var age: Int) {}

  def main(args: Array[String]): Unit = {
    // 样例类的方法
    // apply() 快速创建对象，不需要new
    val p1 = Person("yorick", 23)
    // toString() 默认调用对象的toString方法
    println(p1)
    // equals() 可以通过==的形式，比较两个对象的属性值是否相等
    val p2 = Person("yorick", 23)
    println(p1 == p2)
    // hashCode() 获取对象的hashcode
    println(p1.hashCode() == p2.hashCode())
    // copy() 基于一个对象，快速构建类似的对象
    val p3 = p2.copy(age = 30)
    println(p3)
  }
}
