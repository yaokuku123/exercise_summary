package main

import "fmt"

// 常量用作枚举
const (
	SPRING = 0
	SUMMER = 1
	AUTUMN = 2
	WINTER = 3
)

// iota
const (
	aa = iota // 0
	bb        // 1
	cc        // 2
)

const (
	aaa, bbb = iota*2 + 1, iota*2 + 2 // iota=0, aaa=1,bbb=2
	ccc, ddd                          // iota=1, ccc=3,ddd=4
	// 更换公式
	eee, fff = iota*3 + 2, iota*4 + 3 // iota=2, eee=8,fff=11
	ggg, hhh                          // iota=3, ggg=11,hhh=15
)

func main() {
	// 方式1：显性
	const s1 string = "yorick"
	fmt.Println("s1=", s1)

	// 方式2：隐性
	const s2 = "tom"
	fmt.Println("s2=", s2)

	// 多常量
	const a, b, c = 1, 2, 3
	fmt.Println(a, b, c)

	// 全局常量
	fmt.Println(SPRING, SUMMER, AUTUMN, WINTER)

	// iota
	fmt.Println(aa, bb, cc)

	// iota2
	fmt.Println(aaa, bbb, ccc, ddd, eee, fff, ggg, hhh)
}
