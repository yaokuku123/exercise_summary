package main

import (
	"encoding/json"
	"fmt"
)

type User struct {
	// 可以给字段属性添加tag
	Id   int    `json:"id"`
	Name string `json:"name" doc:"名字"`
	Age  int    `json:"age"`
}

func main() {
	user := User{
		Id:   1,
		Name: "yorick",
		Age:  18,
	}
	// user => json
	userStr, err := json.Marshal(user)
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(string(userStr))

	var convertUser User
	// json => user
	err = json.Unmarshal(userStr, &convertUser)
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(convertUser)
}
