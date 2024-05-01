package main

import "flag"

var serverIp string
var serverPort int

func init() {
	flag.StringVar(&serverIp, "ip", "127.0.0.1", "server ip")
	flag.IntVar(&serverPort, "port", 8999, "server port")
}

func main() {
	// 解析参数
	flag.Parse()
	// 启动客户端
	client := NewClient(serverIp, serverPort)
	client.Run()
}
