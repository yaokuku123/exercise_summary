package main

import "fmt"

// 父类
type Human struct {
	Name string
	Age  int
}

func (this *Human) Eat() {
	fmt.Println("human eat...")
}

// 子类
type Student struct {
	Human // 继承父类
	score int
}

// 重写父类方法
func (this *Student) Eat() {
	fmt.Println("student eat...")
}

func (this *Student) Study() {
	fmt.Println("student study...")
}

func main() {
	// go语言中的继承无法实现多态，需要借助接口实现
	student := Student{Human{Name: "yorick", Age: 20}, 90}
	student.Eat()
	fmt.Println(student)
}
