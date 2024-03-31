package com.yqj.scala.base.class04

object Demo03_ClassAccess {

  class Person {
    // 定义私有成员变量
    private var name: String = _
    private var age = 0

    // 定义成员方法
    def getName() = name

    def setName(name: String) = this.name = name

    def getAge() = age

    def setAge(age: Int) = this.age = age

  }

  def main(args: Array[String]): Unit = {
    // 访问权限修饰符
    val p = new Person
    p.setName("yorick")
    p.setAge(23)
    println(p.getName(), p.getAge())
  }
}
