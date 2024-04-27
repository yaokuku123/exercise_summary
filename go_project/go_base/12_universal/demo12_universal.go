package main

import "fmt"

type Book struct {
	Title  string
	Author string
}

// 万能类型，可以接受任意类型数据
func show(arg interface{}) {
	// interface{}的类型断言机制
	v, ok := arg.(Book)
	if ok {
		fmt.Println("arg is book type")
		fmt.Println(v.Title, v.Author)
	} else {
		fmt.Println("arg is not book type")
		fmt.Println(arg)
	}
}

func main() {
	show(100)
	show(3.14)
	show("abc")
	show(Book{Title: "golang", Author: "yorick"})
}
