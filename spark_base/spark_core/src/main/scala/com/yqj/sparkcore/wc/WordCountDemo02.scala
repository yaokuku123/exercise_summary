package com.yqj.sparkcore.wc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCountDemo02 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("WorldCount")
    val sc = new SparkContext(sparkConf)
    val lines: RDD[String] = sc.textFile("data/wordcount.txt")
    val words: RDD[String] = lines.flatMap(_.split(" "))
    val wordToOne: RDD[(String, Int)] = words.map((_, 1))
    val wordGroup: RDD[(String, Iterable[(String, Int)])] = wordToOne.groupBy(_._1)
    val wordToCount: RDD[(String, Int)] = wordGroup.map {
      case (word, wordList) => {
        wordList.reduce((t1, t2) => {
          (t1._1, t1._2 + t2._2)
        })
      }
    }
    val arr: Array[(String, Int)] = wordToCount.collect()
    arr.foreach(println(_))
    sc.stop()
  }

}
