package main

import "fmt"

func main() {
	// 创建无缓冲的channel
	c := make(chan int)
	go func() {
		defer fmt.Println("goroutine exit")
		fmt.Println("goroutine running...")
		// 先运行到此处则阻塞，等待 num := <-c
		c <- 1 // 将1发送给channel
	}()

	// 先运行到此处则阻塞，等待 c <- 1
	num := <-c // 从channel中接收1
	fmt.Println("num = ", num)
	fmt.Println("main exit")
}
