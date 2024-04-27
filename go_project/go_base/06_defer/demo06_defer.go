package main

import "fmt"

// 栈的顺序释放
func demo() {
	defer fmt.Println("1")
	defer fmt.Println("2")
	defer fmt.Println("3")
}

// 先执行return中的函数，再执行defer
func demo2() int {
	defer func() {
		fmt.Println("demo02 defer...")
	}()

	return func() int {
		fmt.Println("demo02 return...")
		return 10
	}()
}

func demo3(i int) {
	var arr [10]int
	// defer 中捕捉处理异常
	defer func() {
		err := recover()
		if err != nil {
			fmt.Println(err)
		}
	}()
	arr[i] = 10
}

func main() {
	// defer
	// 如果一个函数中有多个defer语句，它们会以LIFO（后进先出）的顺序执行。
	// 作用：1.释放占用的资源
	// 		2.捕捉处理异常
	// 		3.输出日志
	demo()  // 3 -> 2 -> 1
	demo2() // demo02 return... -> demo02 defer...

	demo3(10) // runtime error: index out of range [10] with length 10
}
