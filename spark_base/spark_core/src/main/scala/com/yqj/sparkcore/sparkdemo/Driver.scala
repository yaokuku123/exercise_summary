package com.yqj.sparkcore.sparkdemo

import java.io.ObjectOutputStream
import java.net.Socket


object Driver {
  def main(args: Array[String]): Unit = {
    val client1 = new Socket("localhost", 9999)
    val client2 = new Socket("localhost", 8888)

    val job = new MyJob(datas = List(1, 2, 3, 4), logic = _ * 2)

    val task1 = new Task
    task1.datas = job.datas.take(2)
    task1.logic = job.logic
    val out1 = new ObjectOutputStream(client1.getOutputStream)
    out1.writeObject(task1)
    out1.close()
    client1.close()

    val task2 = new Task
    task2.datas = job.datas.takeRight(2)
    task2.logic = job.logic
    val out2 = new ObjectOutputStream(client2.getOutputStream)
    out2.writeObject(task2)
    out2.close()
    client2.close()
    println("Driver计算分发完成")
  }

}
