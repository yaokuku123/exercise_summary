package com.yqj.scala.base.class12

import scala.util.Random

object Demo01_TraversableOpt {
  def main(args: Array[String]): Unit = {
    // 1.创建
    // 创建空的Traversable对象
    val t1: Traversable[Int] = Traversable.empty[Int]
    val t2: Traversable[Int] = Traversable[Int]()
    val t3: Traversable[Int] = Nil
    // 创建的空对象的值和地址均相同
    println(t1 == t2) // true
    println(t1 eq t2) // true
    // 创建带参数的Traversable对象
    val t4: Traversable[Int] = List(1, 2, 3).toTraversable
    val t5: Traversable[Int] = Traversable(1, 2, 3)
    println(t4)
    println(t5)
    println("-" * 10)

    // 2.转置
    val t_ori: Traversable[Traversable[Int]] = Traversable(Traversable(1, 4, 7), Traversable(2, 5, 8), Traversable(3, 6, 9))
    val t_trans: Traversable[Traversable[Int]] = t_ori.transpose
    println(t_trans)
    println("-" * 10)

    // 3.拼接
    val t6: Traversable[Int] = Traversable(1, 2, 3)
    val t7: Traversable[Int] = Traversable(4, 5)
    val t_concat: Traversable[Int] = Traversable.concat(t6, t7)
    println(t_concat)
    println("-" * 10)

    // 4.筛选元素，collect函数接收一个偏函数
    // 补充：scala.Function1 表示的是一种全定义的函数类型，而 scala.PartialFunction 表示的是一种部分定义的函数类型
    val t8 = (1 to 10).toTraversable
    val t9 = t8.collect {
      case x if x % 2 == 0 => x
    }
    println(t9)
    println("-" * 10)

    // 5.计算集合的阶乘
    // scan == scanLeft
    val t10 = Traversable(1, 2, 3, 4, 5)
    t10.scan(1)(_ * _).foreach(println(_))
    println("-" * 10)

    // 6.元素获取操作
    val t11 = Traversable(1, 2, 3, 4, 5, 6)
    println(t11.head)
    println(t11.last)
    println(t11.headOption)
    println(t11.lastOption) // 避免空集合报错
    println(t11.find(_ % 2 == 0)) // 获取集合中第一个偶数
    println(t11.slice(0, 3)) // 1,2,3
    println("-" * 10)

    // 7.判断集合中的元素是否合法
    val t12 = Traversable(1, 2, 3, 4, 5, 6)
    println(t12.forall(_ % 2 == 0)) // 判断所有元素都为偶数
    println(t12.filter(_ % 2 != 0).size == 0) // 可以实现相同功能，但是需要遍历全部元素
    println(t12.exists(_ % 2 == 0)) // 要求只要有一个元素是偶数即可
    println("-" * 10)

    // 8.聚合操作
    val t13 = Traversable(1, 2, 3, 4)
    println(t13.count(_ % 2 == 0))
    println(t13.sum)
    println(t13.product)
    println(t13.max)
    println(t13.min)
    println("-" * 10)

    // 9.类型转换
    val t14 = Traversable(1, 2, 3)
    val arr = t14.toArray
    val list = t14.toList
    val set = t14.toSet
    println(arr)
    println(list)
    println(set)
    println("-" * 10)

    // 10.填充元素
    // 1.5个元素，每个元素为 hello
    println(Traversable.fill(5)("hello"))
    // 2.3个元素，每个元素是[0,100)之间的随机数
    println(Traversable.fill(3)(Random.nextInt(100)))
    // 3.生成5行2列的集合，每个元素为world
    println(Traversable.fill(5, 2)("world"))
    // 4.通过iterate方法生成集合，从1开始包含5个元素，分别为：1,10,100,1000,10000
    println(Traversable.iterate(1, 5)(_ * 10))
    // 5.通过range方法，获取从1开始，截止数字21之间（包左不包右），间隔为5的所有数据。默认间隔为1
    println(Traversable.range(1, 21, 5))
  }
}
