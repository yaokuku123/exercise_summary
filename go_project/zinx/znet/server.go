package znet

import (
	"errors"
	"fmt"
	"net"
)

type Server struct {
	Name       string
	IpVersion  string
	ServerIp   string
	ServerPort int
}

func NewServer(name, ipVersion, serverIp string, serverPort int) *Server {
	return &Server{
		Name:       name,
		IpVersion:  ipVersion,
		ServerIp:   serverIp,
		ServerPort: serverPort,
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
			dealConn := NewConnection(cid, conn, func(conn *net.TCPConn, buf []byte, cnt int) error {
				_, err := conn.Write(buf[:cnt])
				if err != nil {
					fmt.Println("write err:", err)
					return errors.New("server write error")
				}
				return nil
			})
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
