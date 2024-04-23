package com.yqj.scala.base.class11

// 手动导入隐式参数
object Demo04_ImplicitParam {
  // 前缀和后缀信息是通过隐式参数的形式实现的
  def show(name: String)(implicit delimit: (String, String)): String = delimit._1 + name + delimit._2

  object ImplicitParam {
    implicit val defaultDelimit: (String, String) = "<<<" -> ">>>"
  }

  def main(args: Array[String]): Unit = {
    // 默认参数 VS 隐式参数
    // 默认参数对于函数的用户是可见的，用户可以看到该函数有哪些参数
    // 隐式参数对于函数的用户来说是隐藏的，用户不需要显式地传递这些参数
    // 手动导入隐式参数
    import ImplicitParam.defaultDelimit
    // 使用隐式参数
    println(show("yorick"))
    // 使用自己定义的参数
    println(show("yorick")(("[", "]")))
  }
}

// 自动导入隐式参数
object Demo04_ImplicitParamAuto {

  // 前缀和后缀信息是通过隐式参数的形式实现的
  def show(name: String)(implicit delimit: (String, String)): String = delimit._1 + name + delimit._2

  def main(args: Array[String]): Unit = {
    // 相同作用域下，由程序自动导入
    implicit val defaultDelimit: (String, String) = "<<<" -> ">>>"
    // 使用隐式参数
    println(show("yorick"))
    // 使用自己定义的参数
    println(show("yorick")(("[", "]")))
  }
}
