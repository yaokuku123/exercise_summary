package main

import "fmt"

func modifySlice1(s []int) {
	s[0] = 99 // success
}

func modifySlice2(s []int) {
	s = append(s, 99) // fail
}

func modifySlice3(s *[]int) {
	*s = append(*s, 100)
}

func main() {
	s := []int{1, 2, 3, 4, 5}
	modifySlice1(s) // [99 2 3 4 5]
	fmt.Println(s)
	modifySlice2(s) // [99 2 3 4 5]
	fmt.Println(s)
	modifySlice3(&s) // [99 2 3 4 5 100]
	fmt.Println(s)
}
