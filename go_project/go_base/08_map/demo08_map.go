package main

import "fmt"

func main() {
	// 方式1：声明
	var map1 map[string]string
	if map1 == nil {
		fmt.Println("map1 is empty")
	}
	map1 = make(map[string]string)
	map1["one"] = "golang"
	map1["two"] = "java"
	map1["three"] = "python"
	fmt.Println(map1)

	// 方式2：make
	map2 := make(map[string]string)
	map2["one"] = "golang"
	map2["two"] = "java"
	map2["three"] = "python"
	fmt.Println(map2)

	// 方式3：直接初始化
	map3 := map[string]string{
		"one":   "golang",
		"two":   "java",
		"three": "python",
	}
	fmt.Println(map3)

	// 遍历
	for k, v := range map3 {
		fmt.Println(k, v)
	}
	// 查，获取key，若获取到ok=true，否则为false
	v, ok := map3["one"]
	if ok {
		fmt.Println(v)
	} else {
		fmt.Println("key not found")
	}
	// 改
	map3["three"] = "scala"
	// 增
	map3["four"] = "solidity"
	// 删
	delete(map3, "one")
	fmt.Println(map3)

}
