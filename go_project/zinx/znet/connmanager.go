package znet

import (
	"errors"
	"fmt"
	"github.com/yaokuku123/exercise_summary/go_project/zinx/ziface"
	"sync"
)

type ConnManager struct {
	connMap  map[uint32]ziface.IConnection
	connLock sync.RWMutex
}

func NewConnManager() *ConnManager {
	return &ConnManager{
		connMap: make(map[uint32]ziface.IConnection),
	}
}

// 添加链接
func (this *ConnManager) Add(conn ziface.IConnection) {
	this.connLock.Lock()
	defer this.connLock.Unlock()
	this.connMap[conn.GetConnID()] = conn
	fmt.Println("add conn:", conn.GetConnID(), " success")
}

// 删除链接
func (this *ConnManager) Remove(conn ziface.IConnection) {
	this.connLock.Lock()
	defer this.connLock.Unlock()
	delete(this.connMap, conn.GetConnID())
	fmt.Println("remove conn:", conn.GetConnID(), " success")
}

// 获取链接
func (this *ConnManager) GetConn(connId uint32) (ziface.IConnection, error) {
	this.connLock.RLock()
	defer this.connLock.RUnlock()
	if _, ok := this.connMap[connId]; ok {
		return this.connMap[connId], nil
	} else {
		return nil, errors.New("conn not found")
	}
}

// 获取链接管理器中的链接个数
func (this *ConnManager) Len() int {
	return len(this.connMap)
}

// 清空全部管理器中的链接
func (this *ConnManager) ClearConn() {
	this.connLock.Lock()
	defer this.connLock.Unlock()
	for _, conn := range this.connMap {
		conn.Stop()
		delete(this.connMap, conn.GetConnID())
	}
}
