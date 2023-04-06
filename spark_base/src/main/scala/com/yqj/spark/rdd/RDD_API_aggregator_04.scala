package com.yqj.spark.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDD_API_aggregator_04 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("aggregator")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

    val data = sc.parallelize(List(
      ("zhangsan", 234),
      ("zhangsan", 5667),
      ("zhangsan", 343),
      ("lisi", 212),
      ("lisi", 44),
      ("lisi", 33),
      ("wangwu", 535),
      ("wangwu", 22)
    ))

    // groupByKey 根据k聚合 value->一组
    val group = data.groupByKey()
    group.foreach(println)
    println("-" * 15)

    // 行列转换
    group.flatMap(e=>e._2.map(x =>(e._1,x)).iterator).foreach(println)
    println("-" * 15)
    group.flatMapValues(e=>e.iterator).foreach(println)
    println("-" * 15)

    // sum max min count avg
    val sum = data.reduceByKey(_ + _)
    sum.foreach(println)
    println("-" * 15)
    val max = data.reduceByKey((oldV, newV) => if (oldV > newV) oldV else newV)
    max.foreach(println)
    println("-" * 15)
    val min = data.reduceByKey((oldV, newV) => if (oldV < newV) oldV else newV)
    min.foreach(println)
    println("-" * 15)
    val count = data.mapValues(x => 1).reduceByKey(_ + _)
    count.foreach(println)
    println("-" * 15)
    val avg = sum.join(count).mapValues(e => e._1 / e._2)
    avg.foreach(println)
    println("-" * 15)

    // avg combine
    data.combineByKey(
      (value: Int) => (value, 1),
      (oldValue: (Int, Int), newValue: Int) => (oldValue._1 + newValue, oldValue._2 + 1),
      (v1: (Int, Int), v2: (Int, Int)) => (v1._1 + v2._1, v1._2 + v2._2)
    ).mapValues(e => e._1 / e._2).foreach(println)

    while (true) {

    }

  }
}
