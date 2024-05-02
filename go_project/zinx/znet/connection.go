package znet

import (
	"fmt"
	"github.com/yaokuku123/exercise_summary/go_project/zinx/ziface"
	"net"
)

type Connection struct {
	Id   uint32
	Conn *net.TCPConn
	//该连接的处理方法api
	handleAPI ziface.HandFunc
	isClosed  bool
	ExitChan  chan bool
	Router    ziface.IRouter
}

func NewConnection(id uint32, conn *net.TCPConn, router ziface.IRouter) *Connection {
	return &Connection{
		Id:       id,
		Conn:     conn,
		isClosed: false,
		ExitChan: make(chan bool, 1),
		Router:   router,
	}
}

func (this *Connection) StartReader() {
	fmt.Println("start reader...")
	for {
		// 读取数据
		buf := make([]byte, 1024)
		_, err := this.Conn.Read(buf)
		if err != nil {
			fmt.Println("read err:", err)
			this.ExitChan <- true
			return
		}
		// 封装请求对象
		req := &Request{Conn: this, data: buf}

		// 调用路由异步处理请求
		go func(req ziface.IRequest) {
			this.Router.PreHandle(req)
			this.Router.Handle(req)
			this.Router.PostHandle(req)
		}(req)
	}
}

func (this *Connection) Start() {
	// 处理读请求
	go this.StartReader()

	// 阻塞，等待关闭链接
	for {
		select {
		case <-this.ExitChan:
			return
		}
	}
}

func (this *Connection) Stop() {
	fmt.Println("close connection...")
	// 判断链接是否关闭
	if this.isClosed {
		return
	}
	// 置为关闭
	this.isClosed = true
	// 关闭链接
	this.Conn.Close()
	// 关闭管道
	this.ExitChan <- true
	close(this.ExitChan)
}

func (this *Connection) GetConnID() uint32 {
	return this.Id
}

func (this *Connection) GetTCPConnection() *net.TCPConn {
	return this.Conn
}
