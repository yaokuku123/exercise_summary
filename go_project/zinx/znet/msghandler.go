package znet

import (
	"fmt"
	"github.com/yaokuku123/exercise_summary/go_project/zinx/utils"
	"github.com/yaokuku123/exercise_summary/go_project/zinx/ziface"
)

type MsgHandler struct {
	Apis map[uint32]ziface.IRouter
	// 业务工作Worker池的数量
	WorkerPoolSize uint32
	// 向工作池中发送请求
	TaskQueue []chan ziface.IRequest
}

func NewMsgHandler() *MsgHandler {
	return &MsgHandler{
		Apis:           make(map[uint32]ziface.IRouter),
		WorkerPoolSize: utils.GlobalObject.WorkerPoolSize,
		TaskQueue:      make([]chan ziface.IRequest, utils.GlobalObject.WorkerPoolSize),
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

func (this *MsgHandler) StartOneWorker(workId uint32, taskQueue chan ziface.IRequest) {
	fmt.Println("start worker ", workId, " waiting for task queues")
	// 阻塞等待队列中的请求信息
	for {
		select {
		case request := <-taskQueue:
			this.DoMsgHandler(request)
		}
	}
}

func (this *MsgHandler) StartWorkerPool() {
	for i := 0; i < int(this.WorkerPoolSize); i++ {
		// 创建工作协程的监听请求队列
		this.TaskQueue[i] = make(chan ziface.IRequest, utils.GlobalObject.MaxWorkerTaskLen)
		// 启动工作协程，阻塞等待处理请求
		go this.StartOneWorker(uint32(i), this.TaskQueue[i])
	}
}

func (this *MsgHandler) SendMsgToTaskQueue(request ziface.IRequest) {
	// 根据链接ID遍历找到工作协程ID
	id := request.GetConnection().GetConnID() % this.WorkerPoolSize
	fmt.Println("send msg to workID: ", id, " handle connID", request.GetConnection().GetConnID(), " msgID:", request.GetMsgID())
	// 向该工作协程发送任务
	this.TaskQueue[id] <- request
}
