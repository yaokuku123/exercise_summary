package com.yqj.scala.base.class10

import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}

object Demo02_WriteFile {

  // 序列化和反序列化的对象必须实现 Serializable 接口
  class Person(val name: String, val age: Int) extends Serializable

  // 样例对象自动包含该接口
  case class Person2(val name: String, val age: Int)

  def main(args: Array[String]): Unit = {
    // 向文件中写入数据

    // 向文件中写入指定数据
    val fos = new FileOutputStream("./scala_base/data/4.txt")
    fos.write("hello spark".getBytes())
    fos.close()

    // 对象的序列化和反序列化
    // 对象序列化
    val p1 = new Person("yorick", 23)
    val oos = new ObjectOutputStream(new FileOutputStream("./scala_base/data/5.txt"))
    oos.writeObject(p1)
    oos.close()

    // 对象反序列化
    val ois = new ObjectInputStream(new FileInputStream("./scala_base/data/5.txt"))
    val r_p1 = ois.readObject().asInstanceOf[Person]
    println(r_p1.name, r_p1.age)
    ois.close()
  }
}
