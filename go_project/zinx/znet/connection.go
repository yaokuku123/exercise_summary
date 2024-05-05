package znet

import (
	"errors"
	"fmt"
	"github.com/yaokuku123/exercise_summary/go_project/zinx/utils"
	"github.com/yaokuku123/exercise_summary/go_project/zinx/ziface"
	"io"
	"net"
)

type Connection struct {
	TcpServer ziface.IServer
	Id        uint32
	Conn      *net.TCPConn
	//该连接的处理方法api
	handleAPI  ziface.HandFunc
	isClosed   bool
	ExitChan   chan bool
	MsgHandler ziface.IMsgHandler
	MsgChan    chan []byte
}

func NewConnection(server ziface.IServer, id uint32, conn *net.TCPConn, handler ziface.IMsgHandler) *Connection {
	return &Connection{
		TcpServer:  server,
		Id:         id,
		Conn:       conn,
		isClosed:   false,
		ExitChan:   make(chan bool, 1),
		MsgHandler: handler,
		MsgChan:    make(chan []byte),
	}
}

func (this *Connection) StartReader() {
	fmt.Println("start reader...")
	defer func() {
		fmt.Println("[reader exit]")
		this.Stop()
	}()
	for {
		// 读取数据
		dp := NewDataPack()
		headData := make([]byte, dp.GetHeadLen())
		_, err := io.ReadFull(this.GetTCPConnection(), headData)
		if err != nil {
			fmt.Println("read err:", err)
			return
		}

		msg, err := dp.UnPack(headData)
		if err != nil {
			fmt.Println("unpack err:", err)
			return
		}
		if msg.GetDataLen() == 0 {
			fmt.Println("Msg len is zero")
			return
		}
		data := make([]byte, msg.GetDataLen())
		if _, err := io.ReadFull(this.GetTCPConnection(), data); err != nil {
			fmt.Println("read err:", err)
			return
		}
		msg.SetData(data)
		// 封装请求对象
		req := &Request{Conn: this, Msg: msg}

		// 调用路由异步处理请求
		if utils.GlobalObject.WorkerPoolSize > 0 {
			go this.MsgHandler.SendMsgToTaskQueue(req)
		} else {
			go this.MsgHandler.DoMsgHandler(req)
		}
	}
}

func (this *Connection) StartWriter() {
	fmt.Println("start writer...")
	defer fmt.Println("[writer exit]")
	for {
		select {
		// 处理写数据
		case data := <-this.MsgChan:
			_, err := this.Conn.Write(data)
			if err != nil {
				fmt.Println("write err:", err)
				return
			}
		// 关闭链接
		case <-this.ExitChan:
			return
		}
	}
}

func (this *Connection) Start() {
	// 添加链接至管理器
	this.TcpServer.GetConnMgr().Add(this)
	// 处理读请求
	go this.StartReader()
	// 处理写请求
	go this.StartWriter()
	// 调用回调函数
	this.TcpServer.CallOnConnStart(this)

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
	// 调用关闭链接前的回调函数
	this.TcpServer.CallOnConnStop(this)
	// 关闭链接
	this.Conn.Close()
	// 关闭管道
	this.ExitChan <- true
	// 将自身移除管理器
	this.TcpServer.GetConnMgr().Remove(this)
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
	// 向写管道中填充数据
	this.MsgChan <- msg
	return nil
}
