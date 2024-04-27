package main

import (
	"fmt"
	"github.com/yaokuku123/exercise_summary/go_project/go_base/04_import/lib1"
	_ "github.com/yaokuku123/exercise_summary/go_project/go_base/04_import/lib2" // 不使用该包的函数使用_前缀，但是需要加载该包的init方法
)

func main() {
	// 执行顺序
	// lib1  init...
	// lib2  init...
	// main init...
	// main start...
	// 30
	fmt.Println("main start...")
	fmt.Println(lib1.Add(10, 20))
}

func init() {
	fmt.Println("main init...")
}
