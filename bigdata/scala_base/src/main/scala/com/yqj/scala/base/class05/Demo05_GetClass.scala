package com.yqj.scala.base.class05

object Demo05_GetClass {

  class Person {}

  class Student extends Person {}

  def main(args: Array[String]): Unit = {
    // getClass / classOf
    // isInstanceOf只能判断对象是否为指定类以及其子类的对象，不能精准判断
    // 如果需要精准判断对象的类型 需要使用 getClass和classOf实现

    // p.getClass 可以获取精准的对象类型
    // classOf[类名] 可以精准获取数据类型
    // 使用==操作符直接比较类型
    val p: Person = new Student
    println(p.isInstanceOf[Person]) // true
    println(p.isInstanceOf[Student]) // true
    println(p.getClass == classOf[Person]) // false 实际创建的是Student类型的对象，所以精确的对象应该为Student类型，不是Person类型
    println(p.getClass == classOf[Student]) // true
  }
}
