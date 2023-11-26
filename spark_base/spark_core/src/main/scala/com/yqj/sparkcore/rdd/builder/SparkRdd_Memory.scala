package com.yqj.sparkcore.rdd.builder

import org.apache.spark.{SparkConf, SparkContext}

object SparkRdd_Memory {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // 从内存中加载数据
    val seq = Seq(1,2,3,4)
    // 方法一
//    val rdd = sc.parallelize(seq)
    // 方法二
    val rdd = sc.makeRDD(seq)
    rdd.collect().foreach(println(_))

    sc.stop()
  }

}
