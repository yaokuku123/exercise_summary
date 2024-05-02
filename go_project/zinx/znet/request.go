package znet

import "github.com/yaokuku123/exercise_summary/go_project/zinx/ziface"

type Request struct {
	Conn ziface.IConnection
	Msg  ziface.IMessage
}

func NewRequest(conn ziface.IConnection, msg ziface.IMessage) *Request {
	return &Request{conn, msg}
}

func (this *Request) GetConnection() ziface.IConnection {
	return this.Conn
}

func (this *Request) GetData() []byte {
	return this.Msg.GetData()
}

func (this *Request) GetMsgID() uint32 {
	return this.Msg.GetMsgId()
}
