package main

import (
	"fmt"
	"net"
	"strings"
)

type User struct {
	Name   string
	Addr   string
	C      chan string
	conn   net.Conn
	Server *Server
}

func NewUser(conn net.Conn, server *Server) *User {
	user := &User{
		Name:   conn.RemoteAddr().String(),
		Addr:   conn.RemoteAddr().String(),
		C:      make(chan string),
		conn:   conn,
		Server: server,
	}
	// 启动客户端监听广播go协程
	go user.ListenMsg()
	return user
}

// ListenMsg 监听管道信息，并发送客户端广播消息
func (this *User) ListenMsg() {
	for {
		msg := <-this.C
		fmt.Println(this.Name + " :" + msg)
		_, err := this.conn.Write([]byte(msg + "\n"))
		if err != nil {
			fmt.Println("user send msg err:", err)
			return
		}
	}
}

// SendMsg 向客户端发送信息
func (this *User) SendMsg(msg string) {
	_, err := this.conn.Write([]byte(msg + "\n"))
	if err != nil {
		fmt.Println("user send msg err:", err)
		return
	}
}

// Online 上线
func (this *User) Online(server *Server) {
	server.MapLock.Lock()
	server.OnlineUserMap[this.Name] = this
	server.MapLock.Unlock()
	server.Broadcast(this, "已上线")
}

// Offline 下线
func (this *User) Offline(server *Server) {
	server.MapLock.Lock()
	delete(server.OnlineUserMap, this.Name)
	server.MapLock.Unlock()
	server.Broadcast(this, "已下线")
}

// DoMessage 处理业务
func (this *User) DoMessage(msg string) {
	// 业务处理，定义处理协议
	if msg == "who" {
		// 查询在线客户
		this.Server.MapLock.RLock()
		for _, user := range this.Server.OnlineUserMap {
			onlineMsg := fmt.Sprintf("[%s] %s:在线", user.Addr, user.Name)
			this.SendMsg(onlineMsg)
		}
		this.Server.MapLock.RUnlock()
	} else if len(msg) > 7 && msg[:7] == "rename|" {
		// 对用户重命名
		newName := msg[7:]
		if _, ok := this.Server.OnlineUserMap[newName]; ok {
			this.SendMsg("用户名已存在修改失败")
			return
		}
		this.Server.MapLock.Lock()
		delete(this.Server.OnlineUserMap, this.Name)
		this.Server.OnlineUserMap[newName] = this
		this.Server.MapLock.Unlock()
		this.Name = newName
		this.SendMsg("更新用户名成功：user.Name:" + this.Name)
	} else if len(msg) > 3 && msg[:3] == "to|" {
		// 私聊功能
		remoteName := strings.Split(msg, "|")[1]
		if remoteName == "" {
			this.SendMsg("消息格式不正确，示例：to|zhangsan|hello world")
			return
		}
		remoteUser, ok := this.Server.OnlineUserMap[remoteName]
		if !ok {
			this.SendMsg("该用户名不存在")
			return
		}
		msg := strings.Split(msg, "|")[2]
		if msg == "" {
			this.SendMsg("发送的消息不能为空")
			return
		}
		remoteUser.SendMsg(msg)
	} else {
		// 群聊功能
		this.Server.Broadcast(this, msg)
	}
}
