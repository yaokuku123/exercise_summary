package main

import (
	"fmt"
	"reflect"
)

type User struct {
	// 可以给字段属性添加tag
	Id   int    `json:"id"`
	Name string `json:"name" doc:"名字"`
}

func findTag(arg interface{}) {
	// 反射获取类型信息
	getType := reflect.TypeOf(arg).Elem()
	// 遍历所有字段
	for i := 0; i < getType.NumField(); i++ {
		// 获取info标签
		jsonStr := getType.Field(i).Tag.Get("json")
		// 获取doc标签
		docStr := getType.Field(i).Tag.Get("doc")
		fmt.Println(jsonStr, docStr)
	}
}

func main() {
	user := &User{
		Id:   1,
		Name: "yorick",
	}
	findTag(user)
}
