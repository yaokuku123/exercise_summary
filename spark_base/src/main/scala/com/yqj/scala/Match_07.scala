package com.yqj.scala

object Match_07 {
  def main(args: Array[String]): Unit = {
    val tup = (1.0,88,"abc",false,64)
    val iter: Iterator[Any] = tup.productIterator
    val iterMap = iter.map((x: Any) => {
      x match {
        case 1 => println(s"$x...is 1")
        case w: Int if w > 80 => println(s"$x $w... > 88")
        case false => println(s"$x...is false")
        case w: Int if w > 50 => println(s"$w...is  > 50")
        case _ => println("don not know")
      }
    })
    iterMap.foreach(println)
  }
}
