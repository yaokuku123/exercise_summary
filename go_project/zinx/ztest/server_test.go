package ztest

import (
	"fmt"
	"github.com/yaokuku123/exercise_summary/go_project/zinx/znet"
	"net"
	"testing"
	"time"
)

func TestServer(t *testing.T) {
	// 启动服务端
	server := znet.NewServer("zinx server", "tcp4", "127.0.0.1", 8999)

	// 启动客户端
	go func() {
		time.Sleep(3 * time.Second)
		conn, err := net.Dial("tcp", "127.0.0.1:8999")
		if err != nil {
			fmt.Println("conn err:", err)
			return
		}
		defer conn.Close()
		for {
			// 向服务端写数据
			if _, err := conn.Write([]byte("hello zinx server!")); err != nil {
				fmt.Println("write err:", err)
				return
			}
			// 处理服务端回写数据
			buf := make([]byte, 1024)
			cnt, err := conn.Read(buf)
			if err != nil {
				fmt.Println("read err:", err)
				return
			}
			fmt.Println("<==== resv from server callback msg:", string(buf[:cnt]))
			time.Sleep(1 * time.Second)
		}
	}()

	server.Serve()
}
