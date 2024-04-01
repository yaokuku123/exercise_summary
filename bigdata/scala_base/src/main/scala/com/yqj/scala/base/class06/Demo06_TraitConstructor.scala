package com.yqj.scala.base.class06

object Demo06_TraitConstructor {

  trait Logger {
    println("logger init 2")
  }

  trait MyLogger extends Logger {
    println("my logger init 3")
  }

  trait TimeLogger extends Logger {
    println("time logger init 4")
  }

  class Person {
    println("person init 1")
  }

  class Student extends Person with MyLogger with TimeLogger {
    println("student init 5")
  }

  def main(args: Array[String]): Unit = {
    // trait的构造机制
    // 1.执行父类的构造器
    // 2.按照从左到右的顺序，依次执行trait的构造器
    // 3.如果trait有父trait，则先执行父trait的构造器
    // 4.如果多个trait有同样的父trait，则父trait的构造器只初始化一次
    // 5.执行子类构造器
    val student = new Student
  }
}
