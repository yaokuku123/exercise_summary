package com.yqj.scala.base.class01

object Demo03_String {
  def main(args: Array[String]): Unit = {
    // 定义字符串
    val name = "yorick"
    val age = 23
    val address = "beijing"

    // 字符串拼接的方式
    val result1 = "name=" + name + ",age=" + age + ",address=" + address
    // 插值表达式的方式
    val result2 = s"name=${name},age=${age},address=${address}"
    // 三引号的方式
    val result3 =
      """
        |select
        |  *
        |from
        |  user
        |where
        |  name = "yorick"
        |""".stripMargin
    println(result1)
    println(result2)
    println(result3)
  }
}
