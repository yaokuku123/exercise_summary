package com.yqj.sparkcore.rdd.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Value_Map_Order {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    // map func
    // 1.分区内是一个一个的执行逻辑，只有前面一个数据全部逻辑执行完毕后，才会执行下一个数据
    // 2.分区内执行有序，分区间执行无序
    val dataRDD = sc.makeRDD(List(1, 2, 3, 4), 2)
    val mapRDD1 = dataRDD.map(num => {
      println("^^^^ step1:" + num)
      num
    })
    val mapRDD2 = mapRDD1.map(num => {
      println("#### step2:" + num)
      num
    })
    mapRDD2.collect()
    sc.stop()
  }
}
