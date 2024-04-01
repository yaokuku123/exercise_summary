package com.yqj.scala.base.class05

object Demo06_AbstractClass {

  // 定义抽象类
  // 抽象类的子类：
  // 1.如果也是抽象类，则不用重写父类的抽象方法
  // 2.如何是普通类，则需要重写父类的所有抽象方法
  abstract class Shape {
    // 定义抽象属性
    val name: String

    // 定义抽象方法
    def area(): Double
  }

  // 正方形类继承抽象父类
  class Square(var edge: Double) extends Shape {
    override val name: String = "正方形"

    override def area(): Double = edge * edge
  }

  // 长方形类继承抽象父类
  class Rectangle(var length: Double, var width: Double) extends Shape {
    override val name: String = "长方形"

    override def area(): Double = length * width
  }

  // 圆形类继承抽象父类
  class Circle(var radius: Double) extends Shape {
    override val name: String = "圆形"

    override def area(): Double = Math.PI * radius * radius
  }

  def main(args: Array[String]): Unit = {
    // 抽象类
    // 如果类中有抽象成员（属性/方法），则该类就是一个抽象类
    // 抽象属性：没有初始化值的变量是抽象属性
    // 抽象方法：没有方法体的方法就是抽象方法
    val s1 = new Square(5)
    val s2 = new Rectangle(3, 4)
    val s3 = new Circle(3)
    println(s"${s1.name} area:${s1.area()},${s2.name} area:${s2.area()},${s3.name} area:${s3.area()}")
  }
}
