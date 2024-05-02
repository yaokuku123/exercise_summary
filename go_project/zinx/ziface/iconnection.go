package ziface

import "net"

type IConnection interface {
	// 启动连接
	Start()
	// 关闭连接
	Stop()
	// 获取当前连接ID
	GetConnID() uint32
}

type HandFunc func(*net.TCPConn, []byte, int) error
