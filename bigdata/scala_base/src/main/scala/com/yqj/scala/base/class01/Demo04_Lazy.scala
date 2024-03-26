package com.yqj.scala.base.class01

object Demo04_Lazy {
  def main(args: Array[String]): Unit = {
    // 惰性加载
    // 延迟加载变量，待使用到变量时再加载进内存
    lazy val sql =
    """
      |select
      |  *
      |from
      |  user
      |where
      |  name = "yorick"
      |""".stripMargin
    println(sql)
  }
}
