package com.yqj.sparkcore.wc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCountDemo01 {
  def main(args: Array[String]): Unit = {
    // 1 建立与spark框架的连接
    val sparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    // 2 执行业务操作
    // 读取源文件
    val lines: RDD[String] = sc.textFile("data/wordcount.txt")
    // 对每行数据进行分词
    val words: RDD[String] = lines.flatMap(_.split(" "))
    // 对单词进行分组
    val wordGroup: RDD[(String, Iterable[String])] = words.groupBy(word => word)
    // 对分组后的数据进行转换
    val wordToCount = wordGroup.map {
      case (word, wordList) => {
        (word, wordList.size)
      }
    }
//    val wordToCount = wordGroup.map(x=>(x._1,x._2.size))
    // 采集转换后的数据
    val arr: Array[(String, Int)] = wordToCount.collect()
    arr.foreach(println(_))

    // 3 关闭连接
    sc.stop()
  }

}
