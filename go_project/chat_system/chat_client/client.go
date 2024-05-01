package main

import (
	"fmt"
	"io"
	"net"
	"os"
	"strconv"
)

type Client struct {
	Ip   string
	Port int
	conn net.Conn
	flag int
}

func NewClient(ip string, port int) *Client {
	client := &Client{
		Ip:   ip,
		Port: port,
		flag: 999,
	}
	// 与server建立连接
	conn, err := net.Dial("tcp", ip+":"+strconv.Itoa(port))
	if err != nil {
		fmt.Println("client connect err:", err)
		return nil
	}
	client.conn = conn

	return client
}

func (this *Client) ListenRsp() {
	io.Copy(os.Stdout, this.conn)
}

func (this *Client) SendMsg(msg string) {
	_, err := this.conn.Write([]byte(msg + "\n"))
	if err != nil {
		fmt.Println("send msg err:", err)
		return
	}

}

func (this *Client) Menu() bool {
	var flag int
	fmt.Println("1.查看在线用户")
	fmt.Println("2.修改名称")
	fmt.Println("3.私聊")
	fmt.Println("4.公聊")
	fmt.Println("0:exit")
	fmt.Scanf("%d", &flag)

	if flag >= 0 && flag <= 4 {
		this.flag = flag
		return true
	} else {
		return false
	}
}

func (this *Client) GetOnlineUserList() {
	this.SendMsg("who")
}

func (this *Client) Rename() {
	var newName string
	fmt.Println("请输入新名称: ")
	fmt.Scanf("%s", &newName)
	msg := "rename|" + newName
	this.SendMsg(msg)
}

func (this *Client) PrivateChat() {
	var remoteName string
	this.GetOnlineUserList()
	fmt.Println("请输入聊天的用户名称,exit退出")
	fmt.Scanln(&remoteName)
	for remoteName != "exit" {
		var chatMsg string
		fmt.Println("请输入私聊内容,exit退出")
		fmt.Scanf("%s", &chatMsg)
		for chatMsg != "exit" {
			msg := "to|" + remoteName + "|" + chatMsg
			this.SendMsg(msg)
			chatMsg = ""
			fmt.Println("请输入私聊内容,exit退出")
			fmt.Scanf("%s", &chatMsg)
		}
		remoteName = ""
		fmt.Println("请输入聊天的用户名称,exit退出")
		fmt.Scanf("%s", &remoteName)
	}
}

func (this *Client) PublicChat() {
	var chatMsg string
	fmt.Println("请输入公聊内容,exit退出")
	fmt.Scanf("%s", &chatMsg)
	for chatMsg != "exit" {
		if len(chatMsg) != 0 {
			this.SendMsg(chatMsg)
		}
		chatMsg = ""
		fmt.Println("请输入公聊内容,exit退出")
		fmt.Scanf("%s", &chatMsg)
	}
}

func (this *Client) Run() {
	// 监听server返回的信息并打印至控制台
	go this.ListenRsp()

	for this.flag != 0 {
		for this.Menu() != true {
		}
		switch this.flag {
		case 1:
			// 查看在线用户
			this.GetOnlineUserList()
			break
		case 2:
			// 修改名称
			this.Rename()
			break
		case 3:
			// 私聊
			this.PrivateChat()
			break
		case 4:
			// 公聊
			this.PublicChat()
			break
		}
	}
}
