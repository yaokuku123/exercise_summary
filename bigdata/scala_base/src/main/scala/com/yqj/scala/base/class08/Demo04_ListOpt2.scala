package com.yqj.scala.base.class08

object Demo04_ListOpt2 {
  def main(args: Array[String]): Unit = {
    // 一、List的常用方法
    // 定义一个列表
    val list1 = List(1, 2, 3, 4)
    // 判断列表是否为空
    println(list1.isEmpty)
    // 定义第二个列表
    val list2 = List(4, 5, 6)
    // 拼接两个列表
    val list3 = list1 ++ list2
    println(list3)
    // 获取列表首个元素
    println(list1.head)
    // 获取列表除首个元素之外，其他所有元素
    println(list1.tail)
    // 反转列表元素，不改变原列表
    println(list1.reverse)
    // 获取前缀元素
    // 3表示：前3个元素都是前缀元素
    println(list1.take(3))
    // 获取后缀元素
    // 3表示：前3个元素都是前缀元素，除此之外剩下的都是后缀元素
    println(list1.drop(3))
    println("-" * 10)

    // 二、扁平化操作
    val l1 = List(List(1, 2), List(3), List(4, 5))
    println(l1) // List(List(1, 2), List(3), List(4, 5))
    println(l1.flatten) // List(1, 2, 3, 4, 5)
    println("-" * 10)

    // 三、拉链操作
    val l2 = List("tom", "jerry", "smith")
    val l3 = List(23, 43, 21)
    val listZip = l2.zip(l3)
    val tupleUnzip = listZip.unzip
    println(listZip) // List((tom,23), (jerry,43), (smith,21))
    println(tupleUnzip) // (List(tom, jerry, smith),List(23, 43, 21))
    println("-" * 10)

    // 四、转换字符串
    // toString转换字符串，但是格式无法自定义
    println(l2.toString)
    // mkString转换字符串，格式可以自定义，默认直接拼接无间隔符号
    println(l2.mkString)
    println(l2.mkString(":"))
    println("-" * 10)

    // 五、交并差
    val ll1 = List(1, 2, 3, 4)
    val ll2 = List(3, 4, 5, 6)
    // 两个列表并集，不去重
    val unionList = ll1.union(ll2)
    // 去除其重复元素
    val distinctList = unionList.distinct
    // 两个列表交集
    val intersectList = ll1.intersect(ll2)
    // ll1和ll2的差集
    val diffList = ll1.diff(ll2)
    println(s"不去重并集：${unionList}")
    println(s"去重：${distinctList}")
    println(s"交集：${intersectList}")
    println(s"差集：${diffList}")
  }
}
