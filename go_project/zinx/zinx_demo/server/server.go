package main

import (
	"fmt"
	"github.com/yaokuku123/exercise_summary/go_project/zinx/ziface"
	"github.com/yaokuku123/exercise_summary/go_project/zinx/znet"
)

// 继承zinx的路由对象
type PingRouter struct {
	znet.Router
}

// 注册处理器1
func (this *PingRouter) Handle(request ziface.IRequest) {
	//先读取客户端的数据，再回写ping...ping...ping
	fmt.Println("===> recv from client : msgId=", request.GetMsgID(), ", data=", string(request.GetData()))
	err := request.GetConnection().SendMsg(201, []byte("ping...ping...ping..."))
	if err != nil {
		fmt.Println("send err:", err)
		return
	}
}

// 注册处理器2
type HelloRouter struct {
	znet.Router
}

func (this *HelloRouter) Handle(request ziface.IRequest) {
	//先读取客户端的数据，再回写ping...ping...ping
	fmt.Println("===> recv from client : msgId=", request.GetMsgID(), ", data=", string(request.GetData()))
	err := request.GetConnection().SendMsg(202, []byte("hello world..."))
	if err != nil {
		fmt.Println("send err:", err)
		return
	}
}

func DoConnectionBegin(conn ziface.IConnection) {
	fmt.Println("DoConnecionBegin is Called ... ")
	// 设置自定义属性
	conn.SetProperty("Name", "yorick")
	conn.SetProperty("Age", 27)
	err := conn.SendMsg(201, []byte("DoConnectionBegin..."))
	if err != nil {
		fmt.Println(err)
	}
}

// 连接断开的时候执行
func DoConnectionLost(conn ziface.IConnection) {
	fmt.Println("DoConnectionLost is Called ... ")
	// 获取自定义属性信息
	name, _ := conn.GetProperty("Name")
	age, _ := conn.GetProperty("Age")
	fmt.Println("Name: ", name, " Age: ", age)
}

func main() {
	// 启动服务端
	server := znet.NewServer()
	// 设置回调
	server.SetOnConnStart(DoConnectionBegin)
	server.SetOnConnStop(DoConnectionLost)
	// 添加自定义路由
	server.AddRouter(0, &PingRouter{})
	server.AddRouter(1, &HelloRouter{})

	server.Serve()
}
