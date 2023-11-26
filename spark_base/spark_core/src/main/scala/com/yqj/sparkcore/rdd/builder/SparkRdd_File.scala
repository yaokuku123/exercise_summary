package com.yqj.sparkcore.rdd.builder

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SparkRdd_File {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // 从文件中读取数据
    // 读取单个文件数据
//    val rdd = sc.textFile("data/rdd_data/1.txt")
    // 读取目录下全量数据
//    val rdd = sc.textFile("data/rdd_data")
    // 从HDFS读取数据
//    val rdd = sc.textFile("hdfs://node01:8020/test.txt")
    // 按通配符读取指定文件数据
//    val rdd = sc.textFile("data/rdd_data/1*.txt")
    // 能够区分从哪个文件读取的数据，结果是一个元组，第一个元素表示文件路径，第二个元素为文件数据内容
    val rdd: RDD[(String, String)] = sc.wholeTextFiles("data/rdd_data")

    rdd.collect().foreach(println(_))

    sc.stop()
  }

}
