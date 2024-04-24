package com.yqj.scala.base.class12

import scala.collection.mutable

object Demo05_QueueOpt {
  def main(args: Array[String]): Unit = {
    // 定义可变队列，从队尾依次添加元素1～5
    val q1 = mutable.Queue(1, 2, 3, 4, 5)
    // 往队尾添加元素6
    q1.enqueue(6)
    // 往队尾添加元素7，8，9
    q1.enqueue(7, 8, 9)
    // 移除队列头部第一个元素
    println(q1.dequeue()) // 1
    // 移除队列第一个奇数
    println(q1.dequeueFirst(_ % 2 != 0)) // Some(3)
    // 移除队列所有的偶数
    println(q1.dequeueAll(_ % 2 == 0)) // ArrayBuffer(2, 4, 6, 8)
    println(q1) // Queue(5, 7, 9)
  }
}
