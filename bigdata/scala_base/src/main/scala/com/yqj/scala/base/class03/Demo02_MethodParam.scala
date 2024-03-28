package com.yqj.scala.base.class03

object Demo02_MethodParam {

  def getSum(a: Int = 10, b: Int = 20) = a + b

  def getSum2(nums: Int*) = nums.sum

  def main(args: Array[String]): Unit = {
    // 方法参数
    // 默认参数
    println(getSum())
    // 带名参数
    println(getSum(b = 1))
    // 可变参数
    println(getSum2(1, 2, 3, 4, 5))
  }
}
