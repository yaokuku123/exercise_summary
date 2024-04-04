

package com.yqj {
  class Person {}
  class Teacher {}

  object Demo01_PackageRange {
    def main(args: Array[String]): Unit = {
//      import com.yqj.class07.Student
//      import class07.Student
      val student = new class07.Student
      println(student)
    }
  }

  package class07 {
    class Person {}
    class Student {}

    object Demo01_PackageRange_Sub {
      def main(args: Array[String]): Unit = {
        // 包的作用域
        // 1.下层可以直接访问上层中的内容
        // 2.上层访问下层内容时，可以通过导包（import）或者写全包名的形式实现
        // 3.如果上下层有相同类，采用就近原则，若必须访问上层类，则加全类名

        val teacher = new Teacher
        println(teacher)

        val person = new Person
        println(person)

        val person2 = new com.yqj.Person
        println(person2)
      }
    }
  }
}
