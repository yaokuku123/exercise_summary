package com.yqj.scala.base.class11

object Demo08_TLimit {

  class Person

  class Policeman extends Person

  class Superman extends Policeman

  // 限定该方法的Array元素类型只能是Person类或者Person的子类
  // 并且必须是Policeman类或者Policeman的父类
  def testMethod[T >: Policeman <: Person](arr: Array[T]): Unit = println(arr)

  def main(args: Array[String]): Unit = {
    // 泛型的上下界
    // 如果泛型既有上界，又有下界。则下界写在前面，上界写在后面，例如：[T >: 类型1 <: 类型2]
    testMethod(Array(new Person(), new Person()))
    testMethod(Array(new Policeman(), new Policeman()))
    //    testMethod(Array(new Superman(), new Superman()))  error 运行时报错
    //    testMethod(Array(1, 2)) error 运行时报错
  }
}
