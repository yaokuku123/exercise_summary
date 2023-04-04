package com.yqj.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

object WordCount_01 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("wordcount")
    val sc = new SparkContext(conf)

    val data = sc.textFile("data/wordcount.txt")
    data.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).foreach(println)
  }
}
