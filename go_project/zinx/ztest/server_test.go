package ztest

import (
	"fmt"
	"github.com/yaokuku123/exercise_summary/go_project/zinx/ziface"
	"github.com/yaokuku123/exercise_summary/go_project/zinx/znet"
	"io"
	"net"
	"testing"
	"time"
)

// 继承zinx的路由对象
type PingRouter struct {
	znet.Router
}

// 注册处理器1
func (this *PingRouter) Handle(request ziface.IRequest) {
	//先读取客户端的数据，再回写ping...ping...ping
	fmt.Println("===> recv from client : msgId=", request.GetMsgID(), ", data=", string(request.GetData()))
	err := request.GetConnection().SendMsg(201, []byte("ping...ping...ping..."))
	if err != nil {
		fmt.Println("send err:", err)
		return
	}
}

// 注册处理器2
type HelloRouter struct {
	znet.Router
}

func (this *HelloRouter) Handle(request ziface.IRequest) {
	//先读取客户端的数据，再回写ping...ping...ping
	fmt.Println("===> recv from client : msgId=", request.GetMsgID(), ", data=", string(request.GetData()))
	err := request.GetConnection().SendMsg(202, []byte("hello world..."))
	if err != nil {
		fmt.Println("send err:", err)
		return
	}
}

func TestServer(t *testing.T) {
	// 启动服务端
	server := znet.NewServer()
	// 添加自定义路由
	server.AddRouter(0, &PingRouter{})
	server.AddRouter(1, &HelloRouter{})
	// 启动客户端
	go func() {
		time.Sleep(3 * time.Second)
		conn, err := net.Dial("tcp", "127.0.0.1:7777")
		if err != nil {
			fmt.Println("conn err:", err)
			return
		}
		defer conn.Close()
		for {
			// 向服务端写数据-封包消息
			dp := znet.NewDataPack()
			binaryMsg, err := dp.Pack(znet.NewMessage(1, []byte("hello zinx server")))
			if err != nil {
				fmt.Println("pack err:", err)
				return
			}
			if _, err := conn.Write(binaryMsg); err != nil {
				fmt.Println("write err:", err)
				return
			}
			// 拆包读取
			headData := make([]byte, dp.GetHeadLen())
			if _, err := io.ReadFull(conn, headData); err != nil {
				fmt.Println("read head err:", err)
				return
			}
			msg, err := dp.UnPack(headData)
			if err != nil {
				fmt.Println("unpack err:", err)
				return
			}
			if msg.GetDataLen() == 0 {
				fmt.Println("msg data len is zero")
				return
			}
			data := make([]byte, msg.GetDataLen())
			if _, err := io.ReadFull(conn, data); err != nil {
				fmt.Println("read data err:", err)
				return
			}
			msg.SetData(data)

			fmt.Println("==> Recv Msg: ID=", msg.GetMsgId(), ", len=", msg.GetDataLen(), ", data=", string(msg.GetData()))

			time.Sleep(1 * time.Second)
		}
	}()

	server.Serve()
}
