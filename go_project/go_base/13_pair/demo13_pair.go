package main

import "fmt"

type Reader interface {
	Read()
}
type Writer interface {
	Write()
}

type Book struct {
}

func (this *Book) Read() {
	fmt.Println("Read Book")
}
func (this *Book) Write() {
	fmt.Println("Write Book")
}

func show(arg interface{}) {
	v, ok := arg.(*Book)
	if ok {
		fmt.Printf("*Book type: %T\n", v)
	} else {
		fmt.Println("not Book type")
	}

}

func main() {
	// book: pair<type:*Book,value:book地址>
	book := &Book{}
	// r: pair<type:*Book,value:book地址>
	var r Reader = book
	r.Read()
	// w: pair<type:*Book,value:book地址>
	// 此处为何断言成功：因为 r和w的具体type一致
	// 即：book是类型：*Book/Reader/Writer => pair<type:*Book,value:book地址>
	var w Writer = r.(Writer)
	w.Write()

	show(book)
}
