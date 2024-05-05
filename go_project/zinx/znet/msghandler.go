package znet

import (
	"fmt"
	"github.com/yaokuku123/exercise_summary/go_project/zinx/ziface"
)

type MsgHandler struct {
	Apis map[uint32]ziface.IRouter
}

func NewMsgHandler() *MsgHandler {
	return &MsgHandler{
		Apis: make(map[uint32]ziface.IRouter),
	}
}

// DoMsgHandler 执行路由处理器
func (this *MsgHandler) DoMsgHandler(request ziface.IRequest) {
	msgId := request.GetMsgID()
	if _, ok := this.Apis[msgId]; !ok {
		fmt.Println("msgId:", msgId, " is not exist")
		return
	}
	// 处理具体的函数
	this.Apis[msgId].PreHandle(request)
	this.Apis[msgId].Handle(request)
	this.Apis[msgId].PostHandle(request)
}

// AddRouter 添加路由处理器
func (this *MsgHandler) AddRouter(msgId uint32, router ziface.IRouter) {
	// 判断是否已注册路由
	if _, ok := this.Apis[msgId]; ok {
		panic("duplicate msg id")
	}
	// 添加路由处理器
	this.Apis[msgId] = router
}
