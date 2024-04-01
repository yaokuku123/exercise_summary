package com.yqj.scala.base.class06

object Demo05_TraitDynamic {

  trait Logger {
    def log(msg: String): Unit
  }

  class User {}

  def main(args: Array[String]): Unit = {
    // 特质的动态混入
    // 通过对象的动态混入技术，使得user对象具有Logger特质的log方法
    val user = new User with Logger {
      override def log(msg: String): Unit = println("user dynamic logger")
    }
    user.log("yorick")

    // error,新创建的user对象，没有Logger特质
    //    val user2 = new User
    //    user2.log()
  }
}
