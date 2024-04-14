package com.yqj.scala.base.class09

object Demo04_Regex {
  def main(args: Array[String]): Unit = {
    // 正则
    // 简单使用，校验邮箱合法性
    val email = "helloworld@163.com"
    // 定义一个正则表达式，匹配邮箱的合法性
    // 格式："""正则规则""".r  获取的就是一个正则对象
    val regex = """.+@.+\..+""".r
    // regex：正则对象
    // email：表示被校验的邮箱
    // regex.findAllMatchIn(email)：从email字符串中，获取所有满足regex规则的字符串
    if (regex.findAllMatchIn(email).size != 0) {
      println(s"${email} valid")
    } else {
      println(s"${email} unvalid")
    }
    println("-" * 10)

    // 正则过滤集合中的非法邮箱
    val list = List("helloworld@163.com", "hello.com", "hello@")
    val regex2 = """.+@.+\..+""".r
    list.filter(regex2.findAllMatchIn(_).size == 0).foreach(println(_))
    println("-" * 10)

    // 正则模式匹配运营商
    val emailList = List("yorick@163.com", "128e893283@outlook.com", "hello@")
    val regex3 = """.+@(.+)\..+""".r
    // 偏函数
    val res = emailList.map {
      // 固定格式，若正则表达式有多个分组，则括号中给出多个变量接收，例如 regex3(a,b,c)
      case x@regex3(company) => x -> company
      case x => x -> "not match"
    }
    println(res)
  }
}
