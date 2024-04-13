package com.yqj.scala.base.class08

object Demo08_Iterator {

  def main(args: Array[String]): Unit = {
    // 迭代器
    val list = List(1, 2, 3, 4)
    // 获取迭代器
    val it = list.iterator
    // 判断是否还有下一个元素
    while (it.hasNext) {
      // 获取元素
      println(it.next())
    }
    // 当迭代器使用完毕，再次调用next方法，则抛出异常NoSuchElementException
    //    println(it.next())
  }
}
