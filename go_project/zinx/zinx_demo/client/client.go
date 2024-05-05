package main

import (
	"fmt"
	"github.com/yaokuku123/exercise_summary/go_project/zinx/znet"
	"io"
	"net"
	"time"
)

// 启动客户端
func main() {
	conn, err := net.Dial("tcp", "127.0.0.1:8999")
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
}
