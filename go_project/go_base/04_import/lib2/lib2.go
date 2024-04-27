package lib2

import "fmt"

const PackName = "lib2"

func Mul(a, b int) int {
	return a * b
}

func init() {
	fmt.Println(PackName, " init...")
}
