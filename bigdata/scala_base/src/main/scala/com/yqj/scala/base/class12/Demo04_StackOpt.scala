package com.yqj.scala.base.class12

import scala.collection.mutable

object Demo04_StackOpt {

  def main(args: Array[String]): Unit = {
    // 1.Stack
    // 定义可变栈，存储1～5个数字，注意默认是从后向前的方式进栈
    val s1 = mutable.Stack(1, 2, 3, 4, 5)
    // 获取栈顶元素
    println(s1.top) // 1
    // 添加元素,返回集合本身
    println(s1.push(6)) // Stack(6, 1, 2, 3, 4, 5)
    // 通过pushAll添加序列，从前向后的方式进栈
    println(s1.pushAll(Seq(11, 22, 33))) // Stack(33, 22, 11, 6, 1, 2, 3, 4, 5)
    // 移除栈顶元素并返回该元素
    println(s1.pop()) // 33
    // 情况所有元素
    s1.clear()
    println(s1) // Stack()
    println("-" * 10)

    // 2.ArrayStack
    // 定义数组栈，存储1～5个数字，注意默认是从后向前的方式进栈
    val s2 = mutable.ArrayStack(1, 2, 3, 4, 5)
    // 复制栈顶元素
    s2.dup()
    println(s2) // ArrayStack(1, 1, 2, 3, 4, 5)
    // 保存栈
    s2.preserving {
      s2.clear()
      println(s"s2 clear: ${s2}") // s2 clear: ArrayStack()
    }
    println(s2) // ArrayStack(1, 1, 2, 3, 4, 5)
    println("-" * 10)
  }
}
