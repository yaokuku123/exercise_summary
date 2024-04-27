package lib1

import "fmt"

const PackName = "lib1"

func Add(a, b int) int {
	return a + b
}

func init() {
	fmt.Println(PackName, " init...")
}
