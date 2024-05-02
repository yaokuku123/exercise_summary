package znet

import "github.com/yaokuku123/exercise_summary/go_project/zinx/ziface"

type Request struct {
	Conn ziface.IConnection
	data []byte
}

func NewRequest(conn ziface.IConnection, data []byte) *Request {
	return &Request{conn, data}
}

func (this *Request) GetConnection() ziface.IConnection {
	return this.Conn
}

func (this *Request) GetData() []byte {
	return this.data
}
