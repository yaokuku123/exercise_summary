package com.yqj.scala.base.class04

object Demo08_PrivateThis {

  class Person(private var name: String) {
    // error private[this]修饰的变量只能在本类中使用，伴生对象也无法访问
    //  class Person(private[this] var name: String) {
  }

  object Person {
    def printPerson(p: Person) = println(p.name)
  }

  def main(args: Array[String]): Unit = {
    // private[this] 访问权限
    val p = new Person("yorick")
    Person.printPerson(p)
  }
}
