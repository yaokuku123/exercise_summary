package com.yqj.scala.base.class04

import java.text.SimpleDateFormat
import java.util.Date

object Demo10_ProductDateUtils {

  // 定义日期转换工具类
  object DateUtils {
    var sdf: SimpleDateFormat = _

    def date2String(date: Date, template: String): String = {
      sdf = new SimpleDateFormat(template)
      sdf.format(date)
    }

    def string2Date(dateStr: String, template: String): Date = {
      sdf = new SimpleDateFormat(template)
      sdf.parse(dateStr)
    }

  }

  def main(args: Array[String]): Unit = {
    println(DateUtils.date2String(new Date(), "yyyyMMdd HH:mm:ss"))
    println(DateUtils.string2Date("20230204 12:23:31", "yyyyMMdd HH:mm:ss"))
  }
}
