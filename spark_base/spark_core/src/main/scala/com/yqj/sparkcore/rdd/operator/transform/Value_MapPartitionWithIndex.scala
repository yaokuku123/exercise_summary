package com.yqj.sparkcore.rdd.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Value_MapPartitionWithIndex {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    // mapPartitionsWithIndex func：带分区索引的mapPartitions
    val dataRDD = sc.makeRDD(List(1, 2, 3, 4), 2)
    // TEST1：过滤1号分区
    val mapRDD = dataRDD.mapPartitionsWithIndex((index, iter) => {
      if (index == 1) {
        iter
      } else {
        Nil.iterator
      }
    })
    // TEST2:给数据带上分区索引
    val mapRDD2 = dataRDD.mapPartitionsWithIndex((index, iter) => {
      iter.map((index, _))
    })

    mapRDD2.collect().foreach(println(_))
    sc.stop()
  }
}
