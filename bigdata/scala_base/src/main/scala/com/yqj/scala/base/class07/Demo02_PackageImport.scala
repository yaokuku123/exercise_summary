package com.yqj.scala.base.class07

object Demo02_PackageImport {
  def main(args: Array[String]): Unit = {
    // 包的引入
    // 1.scala并不是完全引入了scala包和Predef包中的所有内容，部分仍需要先导包
    // 2.import语句可以写到scala文件中任何需要用到的地方
    // 3.导入包中所有的类和特质，通过下划线实现
        // import scala._
    // 4.若仅需要引入几个类和特质，可以通过选取器{}实现
        // import scala.collection.mutable.{HashSet, TreeSet}
    // 5.引入多个包中含有相同的类，可以通过重命名或隐藏的方式解决
        // 重命名
        // import java.util.{HashSet=>JavaHashSet}
        // 隐藏
        // import java.util.{HashSet=>_,_}
  }
}
