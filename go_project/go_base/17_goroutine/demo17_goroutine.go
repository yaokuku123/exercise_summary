package main

import (
	"fmt"
	"time"
)

func main() {
	// 启动go协程
	go func() {
		defer fmt.Println("A defer")
		func() {
			defer fmt.Println("B defer")
			// 如果向在内层函数中直接退出当前go协程
			// runtime.Goexit()
			fmt.Println("B")
		}()
		fmt.Println("A")
	}()
	// 死循环等着
	// 如果主协程挂了，则其他协程直接退出
	for {
		time.Sleep(1 * time.Second)
	}
}
