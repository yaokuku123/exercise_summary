package com.yqj.scala

import java.util

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Collection_04 {
  def main(args: Array[String]): Unit = {
    println("--------1.使用Java的集合---------")
    val javaList: util.List[Int] = new util.ArrayList[Int]()
    javaList.add(10)
    javaList.add(11)
    javaList.forEach(println)

    println("--------2.Array数组---------")
    val arr: Array[Int] = Array(1, 2, 3)
    arr(1) = 99
    println(arr(1))
    arr.foreach(println)

    println("--------3.List链表---------")
    // 不可变
    val list: List[Int] = List(10, 11, 12)
    list.foreach(println)
    // 可变
    val listModify: ListBuffer[Int] = ListBuffer(13, 14, 15)
    listModify.+=(16)
    listModify.foreach(println)

    println("--------4.Set集合---------")
    // 不可变
    val set: Set[Int] = Set(10, 11, 11, 12, 13)
    set.foreach(println)
    // 可变
    val setModify: mutable.Set[Int] = scala.collection.mutable.Set(14, 15, 15, 16)
    setModify.+=(17)
    setModify.foreach(println)

    println("--------5.Tuple元组---------")
    val t2: (String, Int) = Tuple2("yorick", 25)
    val t3: (String, Int, String) = ("tom", 25, "bj")
    println(t2)
    println(t3)
    // 获得tuple的迭代器，遍历元素
    val tupleIter: Iterator[Any] = t3.productIterator
    tupleIter.foreach(println)

    println("--------6.Map映射---------")
    // 不可变
    val map: Map[String, Int] = Map(("yorick", 25), ("tom", 30))
    // 迭代 key 和 value
    val keys: Iterable[String] = map.keys
    val values: Iterable[Int] = map.values
    keys.foreach(println)
    values.foreach(println)
    // 普通获取，若无则抛异常
    println(map("yorick"))
    // 特殊获取，若无则采用默认值返回
    println(map.getOrElse("yorick2", -1))
    // 可变
    val mapModify = scala.collection.mutable.Map(("jerry", 21), ("smith", 45))
    mapModify.put("cathy", 25)
    mapModify.+=(("lucy", 22))
    mapModify.foreach(println)

    println("--------7.艺术---------")
    val listStr = List(
      "hello world",
      "hello msb",
      "good idea"
    )
    // 普通（非艺术），内存扩大了N倍，每一步计算内存都留有对象数据
    val flatMap: List[String] = listStr.flatMap(_.split(" "))
    val res: List[(String, Int)] = flatMap.map((_, 1))
    res.foreach(println)

    // 迭代器（艺术），listStr 真正的数据集有数据。iter.flatMap  iter.map 没有发生计算，返回了一个新的迭代器
    val iter: Iterator[String] = listStr.iterator
    val iterFlatMap: Iterator[String] = iter.flatMap(_.split(" "))
    //    iterFlatMap.foreach(println) // 迭代器的方式，仅可遍历一次，若执行此步，则后续迭代器指针已指向List结尾
    val iterRes = iterFlatMap.map((_, 1))
    iterRes.foreach(println)

  }
}
