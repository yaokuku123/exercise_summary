package main

import "fmt"

func fib(c chan int, quit chan bool) {
	x, y := 1, 1
	for {
		// 监听多个channel
		select {
		case c <- x:
			x, y = y, x+y
		case <-quit:
			fmt.Println("quit")
			return
		}
	}
}

func main() {
	c := make(chan int)
	quit := make(chan bool)
	go func() {
		// 消费10次channel数据
		for i := 0; i < 10; i++ {
			fmt.Println(<-c)
		}
		quit <- true
	}()
	fib(c, quit)
}
