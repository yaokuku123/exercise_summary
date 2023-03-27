package com.yqj.scala

trait Walk{
  def slowWalk(): Unit = {
    println("slow walk")
  }

  def quickWalk():Unit
}

trait Fly {
  def fly(): Unit = {
    println("fly")
  }
}

class Duck extends Walk with Fly {
  override def quickWalk(): Unit = {
    println("duck quick walk")
  }

  def call(): Unit = {
    println("duck call")
  }
}

object Trait_06 {
  def main(args: Array[String]): Unit = {
    val duck = new Duck()
    duck.call()
    duck.fly()
    duck.slowWalk()
    duck.quickWalk()
  }
}
