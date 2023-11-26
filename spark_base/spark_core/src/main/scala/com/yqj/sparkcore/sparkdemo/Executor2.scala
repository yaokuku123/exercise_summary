package com.yqj.sparkcore.sparkdemo

import java.io.ObjectInputStream
import java.net.ServerSocket

object Executor2 {
  def main(args: Array[String]): Unit = {
    // 启动服务
    val serverSocket = new ServerSocket(8888)
    println("executor2 start success")
    val conn = serverSocket.accept()
    // 创建对象输入流
    val objIn = new ObjectInputStream(conn.getInputStream)
    val task = objIn.readObject().asInstanceOf[Task]
    // 计算任务
    val res = task.compute()
    println(s"executor2 [8888] exec success, res: ${res}")
    objIn.close()
    conn.close()
    serverSocket.close()
  }
}
