package znet

import (
	"errors"
	"fmt"
	"github.com/yaokuku123/exercise_summary/go_project/zinx/ziface"
	"io"
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
		dp := NewDataPack()
		headData := make([]byte, dp.GetHeadLen())
		_, err := io.ReadFull(this.GetTCPConnection(), headData)
		if err != nil {
			fmt.Println("read err:", err)
			this.ExitChan <- true
			continue
		}

		msg, err := dp.UnPack(headData)
		if err != nil {
			fmt.Println("unpack err:", err)
			this.ExitChan <- true
			continue
		}
		if msg.GetDataLen() == 0 {
			fmt.Println("Msg len is zero")
			this.ExitChan <- true
			continue
		}
		data := make([]byte, msg.GetDataLen())
		if _, err := io.ReadFull(this.GetTCPConnection(), data); err != nil {
			fmt.Println("read err:", err)
			continue
		}
		msg.SetData(data)
		// 封装请求对象
		req := &Request{Conn: this, Msg: msg}

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

func (this *Connection) SendMsg(id uint32, data []byte) error {
	// 失去链接
	if this.isClosed {
		return errors.New("connection is closed")
	}
	// 打包
	dp := NewDataPack()
	msg, err := dp.Pack(NewMessage(id, data))
	if err != nil {
		fmt.Println("pack err:", err)
		return errors.New("pack err")
	}
	// 发送
	_, err = this.Conn.Write(msg)
	if err != nil {
		fmt.Println("send err:", err)
		return errors.New("send err")
	}
	return nil
}
