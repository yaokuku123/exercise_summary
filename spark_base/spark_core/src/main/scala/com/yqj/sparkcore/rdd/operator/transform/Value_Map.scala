package com.yqj.sparkcore.rdd.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Value_Map {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    // map func
    val dataRDD = sc.makeRDD(List(1, 2, 3))

    // method1 显式函数
    def mapFunction(num: Int): Int = {
      num * 2
    }

    //    val mapRDD = dataRDD.map(mapFunction)
    // method2 匿名函数
    //    val mapRDD = dataRDD.map((num:Int)=>{num * 2})
    // method3 简化匿名函数
    val mapRDD = dataRDD.map(_ * 2)
    mapRDD.collect().foreach(println(_))
    sc.stop()
  }
}
