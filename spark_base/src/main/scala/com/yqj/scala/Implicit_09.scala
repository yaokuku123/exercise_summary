package com.yqj.scala

import java.util

object Implicit_09 {
  def main(args: Array[String]): Unit = {
    val listLinked = new util.LinkedList[Int]()
    listLinked.add(1)
    listLinked.add(2)
    listLinked.add(3)
    val listArray = new util.ArrayList[Int]()
    listArray.add(1)
    listArray.add(2)
    listArray.add(3)

    println("--------1.隐式转换变量---------")
    implicit val value: String = "yorick"

    def func01(age: Int)(implicit name: String): Unit = {
      println(s"name: $name, age: $age")
    }

    func01(25)("tom")
    func01(25)

    println("--------2.隐式转换类---------")
    //    implicit class MyClass[T](list: util.ArrayList[T]) {
    //      def foreach(f: T => Unit): Unit = {
    //        val iter = list.iterator()
    //        while (iter.hasNext) {
    //          f(iter.next())
    //        }
    //      }
    //    }
    //    listArray.forEach(println)

    println("--------3.隐式转换函数---------")

    implicit def func02[T](list: util.ArrayList[T]) = {
      val iter = list.iterator()
      new MyClass2(iter)
    }

    implicit def func03[T](list: util.LinkedList[T]) = {
      val iter = list.iterator()
      new MyClass2(iter)
    }

    listArray.foreach(println)
    listLinked.foreach(println)
  }
}

class MyClass2[T](iter: util.Iterator[T]) {
  def foreach(f: T => Unit): Unit = {
    while (iter.hasNext) {
      f(iter.next())
    }
  }
}
