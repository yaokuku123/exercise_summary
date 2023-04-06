package com.yqj.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

object RDD_API_partitions_05 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("partitions")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

    val data = sc.parallelize(1 to 10, 2)

    // 方法一：频繁打开和关闭数据库连接
    data.map(x => {
      println("connect mysql")
      println(s"select $x from mysql")
      println("close mysql")
      x + " selected"
    }).foreach(println)
    println("-" * 15)

    // 方法二：每个分区打开关闭一次数据库连接，但会使得中间开辟内存存储临时数据，数据在内存积压
    data.mapPartitionsWithIndex((pindex, piter) => {
      val ls = new ListBuffer[String]
      println(s"$pindex connect mysql")
      while (piter.hasNext) {
        val v: Int = piter.next()
        println(s"$pindex select $v from mysql")
        ls += v + " selected"
      }
      println(s"$pindex close mysql")
      ls.iterator
    }).foreach(println)
    println("-" * 15)

    // 方法三：每个分区打开关闭一次数据库连接，数据不会在内存积压
    data.mapPartitionsWithIndex((pindex, piter) => {
      new Iterator[String] {
        println(s"$pindex connect mysql")

        override def hasNext: Boolean = {
          if (!piter.hasNext) {
            println(s"$pindex close mysql")
            false
          } else {
            true
          }
        }

        override def next(): String = {
          val v: Int = piter.next()
          println(s"$pindex select $v from mysql")
          v + " selected"
        }
      }
    }).foreach(println)

  }
}
