package com.yqj.sparkcore.rdd.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Value_FlatMap {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    // flatMap func：扁平化
    // TEST1:多维列表扁平化
    val dataRDD = sc.makeRDD(List(List(1, 2), List(3, 4)))
    val flatMapRDD = dataRDD.flatMap(list => list)
    // TEST2：字符串扁平化
    val dataRDD2 = sc.makeRDD(List("hello spark", "hello scala"))
    val flatMapRDD2 = dataRDD2.flatMap(_.split(" "))
    // TEST3：多种类型的集合扁平化
    val dataRDD3 = sc.makeRDD(List(List(1, 2), 3,"hello", List(4, 5)))
    val flatMapRDD3 = dataRDD3.flatMap(data => {
      data match {
        case list: List[_] => list
        case dat => List(dat)
      }
    })
    // 简化模式匹配
    val dataRDD4 = sc.makeRDD(List(List(1, 2), 3,"hello", List(4, 5)))
    val flatMapRDD4 = dataRDD4.flatMap {
      case list: List[_] => list
      case dat => List(dat)
    }

    flatMapRDD4.collect().foreach(println(_))
    sc.stop()
  }
}
