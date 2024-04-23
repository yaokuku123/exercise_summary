package com.yqj.scala.base.class10

import java.io.{File, FileInputStream}

import scala.io.Source

object Demo01_ReadFile {
  def main(args: Array[String]): Unit = {
    // 按行读取文件
    // 1.获取数据源文件对象，默认使用utf-8
    val source = Source.fromFile("./scala_base/data/1.txt", "utf-8")
    // 2.以行为单位读取数据
    val it = source.getLines()
    // 3.将读取的数据封装到列表
    val list = it.toList
    for (data <- list) println(data)
    // 4.关闭Source对象
    source.close()
    println("-" * 10)

    // 按字符读取
    val source2 = Source.fromFile("./scala_base/data/1.txt", "utf-8")
    val it2 = source2.buffered
    while (it2.hasNext) {
      print(it2.next())
    }
    source2.close()
    println()
    println("-" * 10)

    // 若文件不大，则可以直接读取为字符串
    val source3 = Source.fromFile("./scala_base/data/1.txt", "utf-8")
    val data = source3.mkString
    println(data)
    source3.close()
    println("-" * 10)

    // 读取词法单元和数字
    val source4 = Source.fromFile("./scala_base/data/2.txt", "utf-8")
    // 将数据读取封装为一个字符串，再通过切割获取字符串数组
    // \s:表示空白字符，即：空格 \t \r \n
    // \:表示转义字符，需要\\才能表示一个\
    val strArr = source4.mkString.split("\\s+")
    // 将字符串类型的数据转换为int类型，然后数据值+1并打印
    strArr.map(_.toInt + 1).foreach(println(_))
    source4.close()
    println("-" * 10)

    // 从其他字符串中读取数据
    val source5 = Source.fromString("hello world")
    val str = source5.mkString
    println(str)
    source5.close()
    println("-" * 10)

    val source6 = Source.fromURL("https://www.baidu.com")
    val urlData = source6.mkString
    println(urlData)
    source6.close()
    println("-" * 10)

    // 读取二进制文件
    val file = new File("./scala_base/data/pic1.png")
    val fis = new FileInputStream(file)
    val buf = new Array[Byte](file.length().toInt)
    val len = fis.read(buf)
    println(len)
    fis.close()
  }
}
