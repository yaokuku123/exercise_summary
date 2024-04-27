package main

import "fmt"

// 类型别名
type myInt int

// 定义自定义对象
type Book struct {
	Title  string
	Author string
}

// 如果为Book，则表示副本，无法对传递对象进行修改，需要为*Book表示调用对象的指针，可以进行值修改
func (this *Book) ChangeTitle(newTitle string) {
	this.Title = newTitle
}

func main() {
	var a myInt = 10
	// a = 10, type = main.myInt
	fmt.Printf("a = %d, type = %T\n", a, a)

	// 创建对象
	book := Book{Title: "Go Programming Language", Author: "Robert Ken"}
	fmt.Printf("book = %v\n", book)
	book.ChangeTitle("Go Programming Language2")
	fmt.Printf("book = %v\n", book)
}
