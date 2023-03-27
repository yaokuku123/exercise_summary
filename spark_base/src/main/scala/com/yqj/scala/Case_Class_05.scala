package com.yqj.scala

case class Dog(name:String,age:Int) {

}


object Case_Class_05 {
  def main(args: Array[String]): Unit = {
    val d1 = Dog("hsq", 25)
    val d2 = Dog("hsq", 25)
    println(d1 == d2)
    println(d1.equals(d2))
  }
}
