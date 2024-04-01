package com.yqj.scala.base.class06

object Demo02_TraitMulti {

  trait MessageSender {
    def send(msg: String): Unit
  }

  trait MessageReceiver {
    def receiver(): Unit
  }

  // 类继承多个特质
  class MessageWorker extends MessageSender with MessageReceiver {
    override def send(msg: String): Unit = println(s"send msg: ${msg}")

    override def receiver(): Unit = println("receive msg")
  }

  def main(args: Array[String]): Unit = {
    // 类继承多个特质
    val worker = new MessageWorker
    worker.send("hello scala")
    worker.receiver()
  }
}
