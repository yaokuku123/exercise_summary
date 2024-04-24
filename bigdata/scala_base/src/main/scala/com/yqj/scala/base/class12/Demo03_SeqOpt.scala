package com.yqj.scala.base.class12

object Demo03_SeqOpt {
  def main(args: Array[String]): Unit = {
    // 1.获取序列的长度和元素
    val seq1 = (1 to 5).toSeq
    println(seq1.size, seq1.length) // 长度5
    println(seq1(2), seq1.apply(2)) // 元素3
    println("-" * 10)

    // 2.获取指定元素的索引
    val seq2 = Seq(1, 2, 4, 6, 4, 3, 2)
    // 获取元素2第一次输出的索引
    println(seq2.indexOf(2)) // 1
    // 获取元素2最后一次输出的索引
    println(seq2.lastIndexOf(2)) // 6
    // 获取小于5的第一个偶数的索引
    println(seq2.indexWhere(x => x < 5 && x % 2 == 0)) // 1
    // 从索引2开始找，获取小于5的第一个偶数的索引
    println(seq2.indexWhere(x => x < 5 && x % 2 == 0, 2)) // 2
    // 获取小于5的最后一个偶数的索引
    println(seq2.lastIndexWhere(x => x < 5 && x % 2 == 0)) // 6
    // 子序列Seq(1,2)第一次出现的索引
    println(seq2.indexOfSlice(Seq(1, 2))) // 0
    // 从索引3开始找，子序列Seq(1,2)第一次出现的索引
    println(seq2.indexOfSlice(Seq(1, 2), 3)) // -1
    println("-" * 10)

    // 3.判断集合是否包含指定的元素
    val seq3 = (1 to 10).toSeq
    println(seq3.startsWith(Seq(1, 2))) // true
    println(seq3.endsWith(Seq(3, 4))) // false
    println(seq3.contains(5)) // true
    println(seq3.containsSlice(Seq(4, 5))) // true
    println(seq3.containsSlice(Seq(1, 3))) // false
    println("-" * 10)

    // 4.修改指定元素（不可变集合的修改是生成新的集合）
    val seq4 = (1 to 5).toSeq
    val seq4_modify1 = seq4.updated(2, 10) // 替换索引2位置的元素值为10
    val seq4_modify2 = seq4.patch(1, Seq(10, 20), 3) // 从索引1开始，用子序列Seq(10,20)替换3个元素
    println(seq4) // 1,2,3,4,5
    println(seq4_modify1) // 1,2,10,4,5
    println(seq4_modify2) // 1,10,20,5
    println("-" * 10)
  }
}
