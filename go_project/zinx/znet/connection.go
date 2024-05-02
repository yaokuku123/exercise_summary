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
}

func NewConnection(id uint32, conn *net.TCPConn, handleAPI ziface.HandFunc) *Connection {
	return &Connection{
		Id:        id,
		Conn:      conn,
		handleAPI: handleAPI,
		isClosed:  false,
		ExitChan:  make(chan bool, 1),
	}
}

func (this *Connection) StartReader() {
	fmt.Println("start reader...")
	for {
		// 读取数据
		buf := make([]byte, 1024)
		cnt, err := this.Conn.Read(buf)
		if err != nil {
			fmt.Println("read err:", err)
			this.ExitChan <- true
			return
		}
		fmt.Println("====> recv client msg:", string(buf[:cnt]))

		// 处理方法
		err = this.handleAPI(this.Conn, buf, cnt)
		if err != nil {
			fmt.Println("handle err:", err)
			this.ExitChan <- true
			return
		}
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
