package com.yqj.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}


// 下载源码包
// mvn dependency:resolve -Dclassifier=sources

// 面向数据集操作：
//*，带函数的非聚合：  map，flatmap
//1，单元素：union，cartesion  没有函数计算
//2，kv元素：cogroup，join   没有函数计算
//3，排序
//4，聚合计算  ： reduceByKey  有函数   combinerByKey

// 面向数据集开发  面向数据集的API  1，基础API   2，复合API
object RDD_API_02 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("api")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

    val dataRdd = sc.parallelize(List(1, 2, 3, 4, 5, 4, 3, 2, 1))
    // 一 带函数的非聚合操作
    // 1 映射操作 map()
    // 2 扁平化操作 flatMap()
    // 3 过滤操作 filter()
    // foreach 遍历操作
    dataRdd.filter(_ > 3).foreach(println)
    println("-" * 15)
    // collect 收集结果到driver
    val arr: Array[Int] = dataRdd.filter(_ > 4).collect()
    arr.foreach(println)
    println("-" * 15)

    // 二 去重
    // 方法1
    dataRdd.map((_, 1)).reduceByKey(_ + _).map(_._1).foreach(println)
    println("-" * 15)
    // 方法2
    dataRdd.distinct().foreach(println)
    println("-" * 15)

    // 三 交并差 笛卡尔积
    val rdd1 = sc.parallelize(List(1, 2, 3, 4, 5))
    val rdd2 = sc.parallelize(List(3, 4, 5, 6, 7))

    // 交
    rdd1.intersection(rdd2).foreach(println)
    println("-" * 15)
    // 并
    rdd1.union(rdd2).foreach(println)
    println("-" * 15)
    // 差
    rdd1.subtract(rdd2).foreach(println)
    println("-" * 15)
    // 笛卡尔积
    rdd1.cartesian(rdd2).foreach(println)
    println("-" * 15)

    val kv1 = sc.parallelize(List(
      ("zhangsan", 11),
      ("zhangsan", 12),
      ("lisi", 13),
      ("wangwu", 14)
    ))
    val kv2 = sc.parallelize(List(
      ("zhangsan", 21),
      ("zhangsan", 22),
      ("lisi", 23),
      ("zhaoliu", 28)
    ))

    // 四 关联
    kv1.cogroup(kv2).foreach(println)
    println("-" * 15)
    // 内关联
    kv1.join(kv2).foreach(println)
    println("-" * 15)
    // 左外关联
    kv1.leftOuterJoin(kv2).foreach(println)
    println("-" * 15)
    // 右外关联
    kv1.rightOuterJoin(kv2).foreach(println)
    println("-" * 15)
    // 全外关联
    kv1.fullOuterJoin(kv2).foreach(println)

  }

}
