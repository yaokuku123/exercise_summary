package ziface

import "net"

type IConnection interface {
	// 启动连接
	Start()
	// 关闭连接
	Stop()
	// 获取当前连接ID
	GetConnID() uint32
	// 获取链接
	GetTCPConnection() *net.TCPConn
	// 发送封装好的消息
	SendMsg(uint32, []byte) error
}

type HandFunc func(*net.TCPConn, []byte, int) error
