package main

import "fmt"

func swap1(a, b int) {
	var tmp int
	tmp = a
	a = b
	b = tmp
}

func swap2(a, b *int) {
	var tmp int
	tmp = *a
	*a = *b
	*b = tmp
}

func main() {
	// 基础数据类型，自定义struct，定长数组都是值传递
	// slice，map，interface{}都是指针传递
	a, b := 10, 20
	fmt.Println("before: ", a, b) // before:  10 20
	swap1(a, b)
	fmt.Println("swap1: ", a, b) // swap1:  10 20
	swap2(&a, &b)
	fmt.Println("swap2: ", a, b) // swap2:  20 10
}
