package znet

import (
	"fmt"
	"github.com/yaokuku123/exercise_summary/go_project/zinx/utils"
	"github.com/yaokuku123/exercise_summary/go_project/zinx/ziface"
	"net"
)

type Server struct {
	Name       string
	IpVersion  string
	ServerIp   string
	ServerPort int
	MsgHandler ziface.IMsgHandler
}

func NewServer() *Server {
	return &Server{
		Name:       utils.GlobalObject.Name,
		IpVersion:  "tcp4",
		ServerIp:   utils.GlobalObject.ServerIp,
		ServerPort: utils.GlobalObject.ServerPort,
		MsgHandler: NewMsgHandler(),
	}
}

// Start 启动服务器
func (this *Server) Start() {
	fmt.Println("server start")
	fmt.Printf("[server config] Name:%s Version:%s IP:%s Port:%d\n", utils.GlobalObject.Name, utils.GlobalObject.Version, utils.GlobalObject.ServerIp, utils.GlobalObject.ServerPort)
	fmt.Printf("[zinx config] MaxPackageSize:%d MaxConn:%d\n", utils.GlobalObject.MaxPackageSize, utils.GlobalObject.MaxConn)
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
				continue
			}
			// 封装链接对象
			dealConn := NewConnection(cid, conn, this.MsgHandler)
			// 异步启动客户端
			go dealConn.Start()
			cid++
		}
	}()
}

// Stop 关闭服务器
func (this *Server) Stop() {
	fmt.Println("server stop")
}

// Serve 运行服务器
func (this *Server) Serve() {
	// 启动服务器
	this.Start()

	// TODO 其他的一些处理

	// 阻塞
	select {}
}

// AddRouter 添加路由
func (this *Server) AddRouter(msgId uint32, router ziface.IRouter) {
	this.MsgHandler.AddRouter(msgId, router)
}
