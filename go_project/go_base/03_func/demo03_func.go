package main

import "fmt"

// 函数多个返回值
func f1(a, b int) (int, int) {
	return a + 1, b + 1
}

// 函数多个返回值，给返回值命名(函数中的局部变量)
func f2(a, b int) (aa, bb int) {
	aa = a + 2
	bb = b + 3
	return
}

func main() {
	fmt.Println(f1(10, 12))
	fmt.Println(f2(10, 12))
}
