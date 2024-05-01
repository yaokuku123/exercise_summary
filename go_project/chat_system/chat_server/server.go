package main

import (
	"fmt"
	"io"
	"net"
	"sync"
	"time"
)

type Server struct {
	Ip            string
	Port          int
	OnlineUserMap map[string]*User
	MapLock       sync.RWMutex
	MessageChan   chan string
}

func NewServer(ip string, port int) *Server {
	server := &Server{
		Ip:            ip,
		Port:          port,
		OnlineUserMap: make(map[string]*User),
		MessageChan:   make(chan string),
	}
	return server
}

// Broadcast server广播事件
func (this *Server) Broadcast(user *User, msg string) {
	sendMsg := fmt.Sprintf("[%s] %s:%s", user.Addr, user.Name, msg)
	this.MessageChan <- sendMsg
}

// ListenMsg server监听广播信息
func (this *Server) ListenMsg() {
	// 向在线客户端广播信息
	for {
		msg := <-this.MessageChan
		this.MapLock.RLock()
		for _, user := range this.OnlineUserMap {
			user.C <- msg
		}
		this.MapLock.RUnlock()
	}
}

// HandlerConn 处理客户端的响应
func (this *Server) HandlerConn(conn net.Conn) {
	fmt.Println("handle conn from:", conn.RemoteAddr())
	// 连接是否存活判断
	isLive := make(chan bool)
	// 创建客户
	user := NewUser(conn, this)
	// 加入在线列表并广播上线消息
	user.Online(this)
	// 处理客户端读写请求
	go func() {
		for {
			buf := make([]byte, 1024)
			n, err := conn.Read(buf)
			if n == 0 {
				fmt.Println("conn close")
				// 客户端下线并广播下线通知
				user.Offline(this)
				return
			}
			if err != nil && err != io.EOF {
				fmt.Println("conn read err:", err)
				return
			}
			// 获取信息并处理
			msg := string(buf[:n-1])
			user.DoMessage(msg)
			isLive <- true
		}
	}()

	// 客户端当前状态管道监听，长时间未响应则踢出
	for {
		select {
		case <-isLive:
		case <-time.After(time.Second * 60):
			user.SendMsg("you have been kicked out")
			close(user.C)
			conn.Close()
			return
		}
	}
}

// Start 启动Server服务
func (this *Server) Start() {
	fmt.Println("server start")
	// 绑定server监听socket
	listen, err := net.Listen("tcp", fmt.Sprintf("%s:%d", this.Ip, this.Port))
	if err != nil {
		fmt.Println("server listen err:", err)
		return
	}
	defer listen.Close()
	// 启动监听广播信息
	go this.ListenMsg()
	// 循环阻塞等待客户端连接
	for {
		conn, err := listen.Accept()
		if err != nil {
			fmt.Println("server accept err:", err)
			return
		}
		// 简易版，直接启动一个go协程处理客户端响应
		go this.HandlerConn(conn)

	}
}
