package main

import "fmt"

func main() {
	// slice 相比于数组，自动扩容，函数传参无需限制长度大小
	// 1.声明切片
	var slice1 []int
	// 只声明，不开辟空间，切片默认为nil
	if slice1 == nil {
		fmt.Println("slice1 is empty")
	}
	// 允许追加空切片,向切片添加一个元素
	slice1 = append(slice1, 1)
	// 向切片添加多个元素
	slice1 = append(slice1, 1, 2, 3, 4)
	// 另一种形式，数组...展开
	slice1 = append(slice1, []int{5, 6}...)
	// 获取切片的长度，容量
	// slice1= [1 1 2 3 4 5 6]  len= 7  cap= 12
	fmt.Println("slice1=", slice1, " len=", len(slice1), " cap=", cap(slice1))

	// 遍历slice
	for i := 0; i < len(slice1); i++ {
		fmt.Println("i->", i, ", value->", slice1[i])
	}
	fmt.Println("--------------")
	for index, value := range slice1 {
		fmt.Println("index:", index, " value:", value)
	}

	// 2.make切片
	// make([]type, len, cap)
	slice2 := make([]int, 3, 5)
	// slice2= [0 0 0]  len= 3  cap= 5
	fmt.Println("slice2=", slice2, " len=", len(slice2), " cap=", cap(slice2))

	// 3.直接初始化切片
	slice3 := []int{1, 2, 3, 4, 5}
	sliceSub := slice3[1:3] // [1,3)，指向原始切片的指针
	sliceSub[1] = 100
	fmt.Println("sliceSub=", sliceSub) // sliceSub= [2 100]
	fmt.Println("slice3=", slice3)     // slice3= [1 2 100 4 5]

	// 拷贝切片
	sliceCopy := make([]int, len(slice3))
	copy(sliceCopy, slice3)
	fmt.Println("sliceCopy=", sliceCopy)
}
