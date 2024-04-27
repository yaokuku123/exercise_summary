package main

import "fmt"

func main() {
	// 单变量赋值
	// 方式1：声明变量，使用默认值
	var a int
	fmt.Println("a=", a)
	// 方式2：显性
	var b int = 10
	fmt.Println("b=", b)
	// 方式3：隐性，简化类型
	var c = 20
	fmt.Println("c=", c)
	// 方式4：冒等，这种方式不能全局声明
	d := 30
	fmt.Println("d=", d)

	// 多变量赋值
	// 方式1，显性，相同类型变量
	var aa, bb int = 100, 200
	fmt.Println("aa=", aa, ", bb=", bb)
	// 方式1，显性，不同类型变量
	var (
		aa2 bool
		bb2 int
	)
	aa2 = true
	bb2 = 2000
	fmt.Println("aa2=", aa2, ", bb2=", bb2)
	// 方式2：隐性，简化类型
	var cc, dd = 300, 400
	fmt.Println("cc=", cc, ", dd=", dd)
	// 方式3：冒等
	ee, ff := 500, 600
	fmt.Println("ee=", ee, ", ff=", ff)
	// 700赋值被废弃
	_, gg := 700, 800
	fmt.Println("gg=", gg)
}
