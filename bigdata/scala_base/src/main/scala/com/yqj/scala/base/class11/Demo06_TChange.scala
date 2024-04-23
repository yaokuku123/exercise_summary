package com.yqj.scala.base.class11

object Demo06_TChange {

  class Father

  class Son extends Father

  class Pair1[T]

  class Pair2[+T]

  class Pair3[-T]

  def main(args: Array[String]): Unit = {
    // 泛型对象的非变、协变和逆变
    // 非变：Father和Son有父子类关系，但是Pair1[Father]和Pair1[Son]没有关系
    val p1: Pair1[Son] = new Pair1[Son]()
    //        val p2: Pair1[Father] = p1  error

    // 协变：Father和Son有父子类关系，Pair2[Father]和Pair2[Son]也是父子类关系
    val p3: Pair2[Son] = new Pair2[Son]()
    val p4: Pair2[Father] = p3
    println(p4)

    // 逆变：Father和Son有父子类关系，Pair2[Son]和Pair2[Father]也是父子类关系(逆向)
    val p5: Pair3[Father] = new Pair3[Father]()
    val p6: Pair3[Son] = p5
    println(p5)
  }
}
