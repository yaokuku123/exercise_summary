package com.yqj.sparkcore.rdd.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Value_MapPartition_Test {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    // TEST:获取分区内的最大值
    // mapPartitions func：分区map
    val dataRDD = sc.makeRDD(List(1, 2, 3, 4), 2)
    val mapRDD = dataRDD.mapPartitions(iter => {
      List(iter.max).iterator
    })

    mapRDD.collect().foreach(println(_))
    sc.stop()
  }
}
