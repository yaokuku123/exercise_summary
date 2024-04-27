package main

import (
	"fmt"
	"reflect"
)

type User struct {
	Id   int
	Name string
	Age  int
}

func (this *User) SayHello() {
	fmt.Println("hello")
}

func DoFieldAndMethod(arg interface{}) {
	getType := reflect.TypeOf(arg)
	getValue := reflect.ValueOf(arg)
	fmt.Println(getType, getValue) // *main.User &{1 Allen.Wu 25}

	// 获取字段
	// 指针需要先调用.Elem()，获取到指向的对象，再得到该对象中的字段信息
	for i := 0; i < getType.Elem().NumField(); i++ {
		field := getType.Elem().Field(i)
		value := getValue.Elem().Field(i).Interface()
		// Id int = 1
		// Name string = Allen.Wu
		// Age int = 25
		fmt.Println(field.Name, field.Type, "=", value)
	}
	// 获取方法
	// 指针*User上面绑定了一个方法，所以不用调用.Elem()
	// 若调用.Elem()，则表示User上面绑定的方法，当前例子User上面没有绑定方法故为0个方法
	for i := 0; i < getType.NumMethod(); i++ {
		method := getType.Method(i)
		// SayHello func(*main.User)
		fmt.Println(method.Name, method.Type)
	}
}

func main() {
	num := 3.14
	// 反射获取变量的类型和具体值
	fmt.Println(reflect.TypeOf(num))  // float64
	fmt.Println(reflect.ValueOf(num)) // 3.14

	// 1.已知原有类型【进行“强制转换”】
	pointer := &num
	value := num
	// 转换回来指针类型*float64
	convertPointer, ok := reflect.ValueOf(pointer).Interface().(*float64)
	if ok {
		fmt.Println(convertPointer)
	}
	// 转换回来数值类型float64
	convertValue, ok := reflect.ValueOf(value).Interface().(float64)
	if ok {
		fmt.Println(convertValue)
	}

	// 2.未知原有类型【遍历探测其Filed】
	user := &User{1, "Allen.Wu", 25}
	DoFieldAndMethod(user)

	// 注意：reflect.Value是通过reflect.ValueOf(X)获得的，
	// 只有当X是指针的时候，才可以通过reflect修改实际变量X的值，
	// 即：要修改反射类型的对象就一定要保证其值是“addressable”的。
}
