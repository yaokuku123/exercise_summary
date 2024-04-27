package main

import "fmt"

type Animal interface {
	Eat()
}

type Cat struct {
}

func (this *Cat) Eat() {
	fmt.Println("cat eat...")
}

type Dog struct {
}

func (this *Dog) Eat() {
	fmt.Println("dog eat...")
}

func show(animal Animal) {
	animal.Eat()
}

func main() {
	// 接口实现多态
	// 接口是指针，需要指向对象的地址
	var animal Animal = &Cat{}
	animal.Eat() // cat eat...
	show(animal)

	animal = &Dog{}
	animal.Eat() // dog eat...
	show(animal)
}
