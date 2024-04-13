package com.yqj.scala.base.class08

object Demo09_Function {
  def main(args: Array[String]): Unit = {
    // 函数式编程
    val list = List(1, 5, 3)
    // foreach：遍历
    // 简化函数
    // 1.列表中的每个元素类型是确定的，可以通过类型推断自动得出每个元素参数的类型，创建函数时可以省略参数列表的类型
    // 2.当函数参数只在函数体中出现一次而且函数体没有嵌套调用时，可以使用下划线简化函数定义
    list.foreach((x: Int) => {
      println(x)
    })
    println("-" * 5)
    list.foreach(x => println(x))
    println("-" * 5)
    list.foreach(println(_))
    println("-" * 10)

    // map：映射
    list.map((x: Int) => {
      "*" * x
    }).foreach(println(_))
    println("-" * 5)
    list.map(x => "*" * x).foreach(println(_))
    println("-" * 5)
    list.map("*" * _).foreach(println(_))
    println("-" * 10)

    // flatMap：映射并打平
    val list2 = List("hadoop spark flink", "java scala golang")
    // 方法1：先map后flatten
    list2.map(_.split(" ")).flatten.foreach(println(_))
    println("-" * 5)
    // 方法2：直接通过flatMap
    list2.flatMap(_.split(" ")).foreach(println(_))
    println("-" * 10)

    // filter：过滤
    list.filter(_ % 2 != 0).foreach(println(_))
    println("-" * 10)

    // sorted：默认排序
    list.sorted.foreach(println(_))
    println("-" * 5)
    // reverse：降序
    list.sorted.reverse.foreach(println(_))
    println("-" * 10)

    // sortBy：根据指定字段排序
    val list3 = List("01,hadoop", "02,flink", "03,hive", "04,spark")
    list3.sortBy(_.split(",")(1)).foreach(println(_))
    println("-" * 10)

    // sortWith：根据一个自定义函数（规则）排序
    // x:表示前面的元素
    // y:表示后面的元素，x>y表示前面的元素比后面的都大，所以为降序排列
    list.sortWith((x, y) => x > y).foreach(println(_))
    println("-" * 5)
    // 简化
    list.sortWith(_ > _).foreach(println(_))
    println("-" * 10)

    // groupBy:分组
    val list4 = List("yorick" -> "male", "tom" -> "male", "lily" -> "female")
    // 根据性别分组
    val mapGender = list4.groupBy(_._2)
    // 计算每个分组下的人数
    mapGender.map(x => x._1 -> x._2.size).foreach(println(_))
    println("-" * 10)

    // reduce:聚合计算
    println(list.reduce((x, y) => x + y))
    println(list.reduce(_ + _))
    println("-" * 10)

    // fold:折叠，与reduce类似，增添了初始值
    println(list.fold(100)(_ + _))
  }
}
