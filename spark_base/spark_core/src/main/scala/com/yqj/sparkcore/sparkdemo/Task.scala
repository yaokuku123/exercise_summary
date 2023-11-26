package com.yqj.sparkcore.sparkdemo

class Task extends Serializable {
  var datas: List[Int] = _
  var logic: (Int) => Int = _

  def compute() = {
    datas.map(logic)
  }
}
