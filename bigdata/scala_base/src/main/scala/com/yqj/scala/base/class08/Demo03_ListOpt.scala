package com.yqj.scala.base.class08

import scala.collection.mutable.ListBuffer

object Demo03_ListOpt {
  def main(args: Array[String]): Unit = {
    // 一、不可变列表创建
    // 1.方法1，List()
    val list1 = List(1, 2, 3, 4)
    // 2.方法2，Nil创建空列表
    val list2 = Nil
    // 3.方法3，::创建
    val list3 = -2 :: -1 :: Nil
    println(list1)
    println(list2)
    println(list3)
    println("-" * 10)

    // 二、可变列表创建
    // 1.创建空的可变列表
    val mutableList1 = ListBuffer[Int]()
    val mutableList2 = ListBuffer(1, 2, 3, 4)
    println(mutableList1)
    println(mutableList2)
    println("-" * 10)

    // 三、可变列表的常见操作
    val mutableList = ListBuffer(1, 2, 3, 4)
    // 获取第一个元素
    println(mutableList(0))
    // 添加一个元素
    mutableList += 5 // 1,2,3,4,5
    // 追加列表
    mutableList ++= List(5, 6, 7) // 1,2,3,4,5,5,6,7
    // 删除一个元素
    mutableList -= 1 // 2,3,4,5,5,6,7
    // 删除列表
    mutableList --= List(2, 3, 5) // 4,5,6,7
    println(mutableList)
    // 转为不可变列表
    val l = mutableList.toList
    println(l)
    // 转为数组
    val arr = mutableList.toArray
    println(arr)
  }
}
