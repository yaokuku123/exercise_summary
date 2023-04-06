package com.yqj.spark.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDD_API_sort_03 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("api_sort")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

    val data = sc.textFile("data/pvuvdata")

    // PV,只显示top5
    // 43.169.217.152	河北	2018-11-12	1542011088714	3292380437528494072	www.dangdang.com	Login
    // 1 读取一行数据并切分取下标为5的元素，拼接为元组
    data.map(line => (line.split("\t")(5), 1))
      // 2 合并每个网站的pv
      .reduceByKey(_ + _)
      // 3 交换kv
      .map(_.swap)
      // 4 按照k排序，降序
      .sortByKey(false)
      // 5 再交换kv
      .map(_.swap)
      // 6 取前5
      .take(5)
      // 7 打印
      .foreach(println)
    println("-" * 15)

    // UV,只显示top5
    // 1 读取一行数据并切分,取出网站和IP组成元组
    data.map(line => {
      val strs = line.split("\t")
      (strs(5), strs(0))
    })
      // 2 数据去重
      .distinct()
      // 3 网站map映射
      .map(k => (k._1, 1))
      // 4 合并每个网站的uv
      .reduceByKey(_ + _)
      // 5 按照访问数量进行排序，降序
      .sortBy(_._2,false)
      // 6 取前5
      .take(5)
      // 7 打印/**/
      .foreach(println)


  }
}
