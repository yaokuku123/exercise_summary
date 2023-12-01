package com.yqj.sparkcore.rdd.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Value_MapPartition {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    // mapPartitions func: 分区map
    // 可以以分区为单位进行数据转换操作，但会使得整个分区的数据加载到内存，处理完的对象不会被释放存在对象引用
    // 在内存较小且数据量较大时，容易出现内存溢出
    val dataRDD = sc.makeRDD(List(1, 2, 3, 4), 2)
    val mapRDD = dataRDD.mapPartitions(iter => {
      println("####")
      iter.map(_ * 2)
    })
    mapRDD.collect().foreach(println(_))
    sc.stop()
  }
}
