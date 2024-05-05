package znet

import (
	"fmt"
	"github.com/yaokuku123/exercise_summary/go_project/zinx/utils"
	"github.com/yaokuku123/exercise_summary/go_project/zinx/ziface"
	"net"
)

type Server struct {
	Name            string
	IpVersion       string
	ServerIp        string
	ServerPort      int
	MsgHandler      ziface.IMsgHandler
	ConnMgr         ziface.IConnManager
	OnConnStartFunc func(conn ziface.IConnection)
	OnConnStopFunc  func(conn ziface.IConnection)
}

func NewServer() *Server {
	return &Server{
		Name:       utils.GlobalObject.Name,
		IpVersion:  "tcp4",
		ServerIp:   utils.GlobalObject.ServerIp,
		ServerPort: utils.GlobalObject.ServerPort,
		MsgHandler: NewMsgHandler(),
		ConnMgr:    NewConnManager(),
	}
}

// Start 启动服务器
func (this *Server) Start() {
	fmt.Println("server start")
	fmt.Printf("[server config] Name:%s Version:%s IP:%s Port:%d\n", utils.GlobalObject.Name, utils.GlobalObject.Version, utils.GlobalObject.ServerIp, utils.GlobalObject.ServerPort)
	fmt.Printf("[zinx config] MaxPackageSize:%d MaxConn:%d\n", utils.GlobalObject.MaxPackageSize, utils.GlobalObject.MaxConn)
	go func() {
		// 启动工作池
		this.MsgHandler.StartWorkerPool()
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
			// 查看当前的客户端链接数量是否超过管理器的阈值
			if this.ConnMgr.Len() >= utils.GlobalObject.MaxConn {
				fmt.Println("conn pool full")
				conn.Close()
				continue
			}
			// 封装链接对象
			dealConn := NewConnection(this, cid, conn, this.MsgHandler)
			// 异步启动客户端
			go dealConn.Start()
			cid++
		}
	}()
}

// Stop 关闭服务器
func (this *Server) Stop() {
	fmt.Println("server stop")
	this.GetConnMgr().ClearConn()
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

// GetConnMgr 获取链接管理器
func (this *Server) GetConnMgr() ziface.IConnManager {
	return this.ConnMgr
}

func (this *Server) SetOnConnStart(startFunc func(conn ziface.IConnection)) {
	this.OnConnStartFunc = startFunc
}

func (this *Server) SetOnConnStop(stopFunc func(conn ziface.IConnection)) {
	this.OnConnStopFunc = stopFunc
}

func (this *Server) CallOnConnStart(conn ziface.IConnection) {
	if this.OnConnStartFunc != nil {
		fmt.Println("---> CallOnConnStart....")
		this.OnConnStartFunc(conn)
	}
}

func (this *Server) CallOnConnStop(conn ziface.IConnection) {
	if this.OnConnStopFunc != nil {
		fmt.Println("---> CallOnConnStop....")
		this.OnConnStopFunc(conn)
	}
}
