package com.yqj.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

object RDD_API_gaoji_06 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("gaoji")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

    val data = sc.parallelize(1 to 10, 5)

    // 抽样
    // withReplacement 是否可放回
    // fraction 抽样占比（大概）
    // seed 随机数生成种子
    data.sample(true, 0.5, 222).foreach(println) // 可放回，大概占总数的50%，随机数种子222
    println("-" * 15)
    data.sample(true, 0.5, 222).foreach(println) // 由于种子相同，故与上面的结果一致
    println("-" * 15)
    data.sample(true, 0.5, 221).foreach(println)
    println("-" * 15)

    // 原始数据
    // 获取分区数
    println("partitions: " + data.getNumPartitions)
    // 每个分区的元素
    data.mapPartitionsWithIndex((pindex, piter) => {
      piter.map(e => (pindex, e))
    }).foreach(println)
    println("-" * 15)

    // 重置分区
    //    val repartitionsData = data.repartition(3)
    val repartitionsData = data.coalesce(10, true)
    // 获取分区数
    println("partitions: " + repartitionsData.getNumPartitions)
    // 每个分区的元素
    repartitionsData.mapPartitionsWithIndex((pindex, piter) => {
      piter.map(e => (pindex, e))
    }).foreach(println)
    println("-" * 15)

    // shuffle == false
    val res1 = data.coalesce(10, false) // 比原始数据分区多，但不进行shuffle，使得不会打散数据
    // 获取分区数
    println("partitions: " + res1.getNumPartitions)
    // 每个分区的元素
    res1.mapPartitionsWithIndex((pindex, piter) => {
      piter.map(e => (pindex, e))
    }).foreach(println)
    println("-" * 15)

    // shuffle == false
    val res2 = data.coalesce(3, false) // 比原始数据分区少，但不进行shuffle，不打散，直接不同分区进行合并即可
    // 获取分区数
    println("partitions: " + res2.getNumPartitions)
    // 每个分区的元素
    res2.mapPartitionsWithIndex((pindex, piter) => {
      piter.map(e => (pindex, e))
    }).foreach(println)
    println("-" * 15)


    while (true) {

    }


  }

}
