package com.yqj.scala.base.class09

object Demo01_Match {

  case class Customer(name: String, age: Int)

  case class Order(id: Int)

  def main(args: Array[String]): Unit = {
    // 简单模式匹配
    val s = "spark"
    val res = s match {
      case "hadoop" => "大数据分布式存储和计算框架"
      case "zookeeper" => "大数据分布式协调服务框架"
      case "spark" => "大数据分布式内存计算框架"
      case _ => "未匹配"
    }
    println(res)
    println("-" * 10)

    // 匹配类型
    // 如果case表达式无需使用匹配到的变量，可以使用下划线代替
    val s2: Any = 1.3
    val res2 = s2 match {
      case x: String => s"${x} is String"
      case x: Int => s"${x} is Int"
      case x: Double => s"${x} is Double"
      case _ => "not match type"
    }
    println(res2)
    val res22 = s2 match {
      case _: String => "String"
      case _: Int => "Int"
      case _: Double => "Double"
      case _ => "not match type"
    }
    println(res22)
    println("-" * 10)

    // 守卫
    val s3 = 2
    s3 match {
      case x if x >= 0 && x <= 3 => println("[0-3]")
      case x if x >= 4 && x <= 8 => println("[4-8]")
      case _ => println("not match")
    }
    println("-" * 10)

    // 匹配样例类
    //    val s4:Any = Customer("yorick", 23)
    val s4: Any = Order(12)
    s4 match {
      case Customer(name, age) => println(s"Customer:${name},${age}")
      case Order(id) => println(s"Order:${id}")
      case _ => println("not match")
    }
    println("-" * 10)

    // 匹配数组
    val arr1 = Array(1, 2, 3)
    val arr2 = Array(0)
    val arr3 = Array(0, 1, 2, 3, 4, 5)
    arr3 match {
      // 匹配长度为3，首元素为1，剩下两个无所谓的数组
      case Array(1, x, y) => println(s"arr1,${x},${y}")
      // 匹配长度为1，首元素为0的数组
      case Array(0) => println(s"arr2")
      // 匹配长度无所谓，首元素为0，后面元素无所谓的数组
      case Array(0, _*) => println(s"arr3")
      case _ => println("not match")
    }
    println("-" * 10)

    // 匹配列表
    val list1 = List(0)
    val list2 = List(0, 1, 2, 3, 4, 5)
    val list3 = List(1, 2)
    // 方式1，List()的方式匹配列表
    list3 match {
      case List(0) => println("list1")
      case List(0, _*) => println("list2")
      case List(x, y) => println(s"list3,${x},${y}")
      case _ => println("not match")
    }
    // 方式2，::的方式匹配列表
    list3 match {
      case 0 :: Nil => println("list1")
      case 0 :: tail => println("list2")
      case x :: y :: Nil => println(s"list3,${x},${y}")
      case _ => println("not match")
    }
    println("-" * 10)

    // 匹配元组
    val tuple1 = (1, 2, 3)
    val tuple2 = (3, 4, 5)
    tuple2 match {
      case (1, x, y) => println(s"tuple1,x:${x},y:${y}")
      case (x, y, 5) => println(s"tuple2,x:${x},y:${y}")
      case _ => println("not match")
    }
    println("-" * 10)

    // 变量声明中的模式匹配
    // Array
    val arr = (0 to 10).toArray
    //    val arr = (0 to 2).toArray error:MatchError,元素数量不匹配，会报错
    val Array(_, x, y, z, _*) = arr
    println(x, y, z)
    println("-" * 5)
    // List
    val list = (0 to 10).toList
    // 方式1，通过List()实现
    val List(a, b, _*) = list
    // 方式2，通过关键字实现 Nil,tail
    val c :: d :: tail = list
    println(a, b)
    println(c, d)
    println("-" * 10)

    // 匹配for表达式
    val map = Map("yorick" -> 23, "tom" -> 31, "jerry" -> 23)
    // 方法1，通过if获取值为23的数据
    for ((k, v) <- map if v == 23) println(k, v)
    // 方式2，通过固定值实现
    for ((k, 23) <- map) println(k, 23)
  }
}
