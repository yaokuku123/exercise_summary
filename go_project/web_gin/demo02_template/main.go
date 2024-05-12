package main

import (
	"fmt"
	"html/template"
	"net/http"
)

func sayHello(w http.ResponseWriter, r *http.Request) {
	// 1.编写模版
	// 2.解析模版
	t, err := template.ParseFiles("./demo02_template/hello.tmpl")
	if err != nil {
		fmt.Println("parse template err:", err)
		return
	}
	// 3.渲染模版
	name := "Gin"
	err = t.Execute(w, name)
	if err != nil {
		fmt.Println("exec template err:", err)
		return
	}
}

func main() {
	// 启动原生http服务，加载template渲染的模版
	http.HandleFunc("/", sayHello)
	err := http.ListenAndServe(":8080", nil)
	if err != nil {
		fmt.Println("Error:", err)
		return
	}
}
