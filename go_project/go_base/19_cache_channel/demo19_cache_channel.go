package main

import (
	"fmt"
	"time"
)

func main() {
	// 创建3个缓冲的channel
	c := make(chan int, 3)
	go func() {
		defer fmt.Println("goroutine exit")
		// channel中可以缓冲3条数据，如果channel中已积压3条则阻塞
		for i := 0; i < 5; i++ {
			c <- i
			fmt.Println("goroutine running,len(c)=", len(c), ", cap(c)=", cap(c))
		}
		close(c)
	}()

	time.Sleep(1 * time.Second)
	// 消费channel，如果channel中无数据则阻塞，如果channel已关闭则消费完channel剩余的数据后跳出循环
	for num := range c {
		fmt.Println("main num=", num)
	}
}
