package com.yqj.sparkcore.wc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCountDemo03 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("WorldCount")
    val sc = new SparkContext(sparkConf)
    val lines: RDD[String] = sc.textFile("data/wordcount.txt")
    val words: RDD[String] = lines.flatMap(_.split(" "))
    val wordToOne: RDD[(String, Int)] = words.map((_, 1))
    val wordToCount: RDD[(String, Int)] = wordToOne.reduceByKey(_+_)
    val arr: Array[(String, Int)] = wordToCount.collect()
    arr.foreach(println(_))
    sc.stop()
  }

}
