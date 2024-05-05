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

func main() {
	// 启动服务端
	server := znet.NewServer()
	// 添加自定义路由
	server.AddRouter(0, &PingRouter{})
	server.AddRouter(1, &HelloRouter{})

	server.Serve()
}
