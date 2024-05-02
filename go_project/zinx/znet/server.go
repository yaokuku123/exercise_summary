package znet

import (
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
	// 异步启动客户端
	go func() {
		for {
			// 等待客户端连接
			conn, err := listener.AcceptTCP()
			if err != nil {
				fmt.Println("accept err:", err)
				return
			}
			// 读写测试
			go func() {
				for {
					// 读取
					buf := make([]byte, 1024)
					cnt, err := conn.Read(buf)
					if err != nil {
						return
					}
					fmt.Println("===> recv from client msg:", string(buf[:cnt]))
					// 回写
					if _, err := conn.Write(buf); err != nil {
						fmt.Println("write err:", err)
						return
					}
				}
			}()
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
