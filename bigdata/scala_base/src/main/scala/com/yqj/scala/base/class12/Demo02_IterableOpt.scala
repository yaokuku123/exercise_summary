package com.yqj.scala.base.class12

object Demo02_IterableOpt {
  def main(args: Array[String]): Unit = {
    // 1.迭代器遍历
    // 方式1：迭代器遍历
    val l1 = (1 to 5).toList
    // 获取迭代器
    val it: Iterator[Int] = l1.iterator
    while (it.hasNext) {
      println(it.next())
    }
    // 方式2：foreach遍历
    l1.foreach(println(_))
    println("-" * 10)

    // 2.迭代器分组遍历
    val l2 = (1 to 13).toList
    // 按照5个元素为1组的方式遍历
    val it2 = l2.grouped(5)
    while (it2.hasNext) {
      println(it2.next())
    }
    println("-" * 10)

    // 3.按照索引生成元组
    val l3 = Iterable("a", "b", "c")
    // ("a"->0)
    val l3_2: Iterable[(String, Int)] = l3.zipWithIndex
    // 转换为 (0->"a")
    l3_2.map(x => x._2 -> x._1).foreach(println(_))
    println("-" * 10)

    // 4.判断集合是否相同
    // sameElements:要求集合的元素及迭代顺序都一致，返回true，否则false
    val l4 = Iterable("a", "b", "c")
    println(l4.sameElements(Iterable("a", "b", "c")))
    println(l4.sameElements(Iterable("a", "c", "b")))
    println(l4.sameElements((Iterable("a", "c", "b", "d"))))
    println("-" * 10)
  }
}
