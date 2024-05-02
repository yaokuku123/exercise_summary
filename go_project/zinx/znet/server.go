package znet

import (
	"fmt"
	"github.com/yaokuku123/exercise_summary/go_project/zinx/ziface"
	"net"
)

type Server struct {
	Name       string
	IpVersion  string
	ServerIp   string
	ServerPort int
	Router     ziface.IRouter
}

func NewServer(name, ipVersion, serverIp string, serverPort int) *Server {
	return &Server{
		Name:       name,
		IpVersion:  ipVersion,
		ServerIp:   serverIp,
		ServerPort: serverPort,
		Router:     nil,
	}
}

// 启动服务器
func (this *Server) Start() {
	fmt.Println("server start")
	go func() {
		// 创建tcp连接地址
		addr, err := net.ResolveTCPAddr(this.IpVersion, fmt.Sprintf("%s:%d", this.ServerIp, this.ServerPort))
		if err != nil {
			fmt.Println("resolve tcp addr err:", err)
			return
		}
		// 监听绑定
		listener, err := net.ListenTCP(this.IpVersion, addr)
		if err != nil {
			fmt.Println("listen tcp err:", err)
			return
		}
		var cid uint32 = 0
		for {
			// 等待客户端连接
			conn, err := listener.AcceptTCP()
			if err != nil {
				fmt.Println("accept err:", err)
				return
			}
			// 封装链接对象
			dealConn := NewConnection(cid, conn, this.Router)
			// 异步启动客户端
			go dealConn.Start()
			cid++
		}
	}()
}

// 关闭服务器
func (this *Server) Stop() {
	fmt.Println("server stop")
}

// 运行服务器
func (this *Server) Serve() {
	// 启动服务器
	this.Start()

	// TODO 其他的一些处理

	// 阻塞
	select {}
}

func (this *Server) AddRouter(router ziface.IRouter) {
	this.Router = router
}
