package ziface

type IMsgHandler interface {
	// DoMsgHandler 执行路由处理器
	DoMsgHandler(IRequest)
	// AddRouter 添加路由处理器
	AddRouter(uint32, IRouter)
	// StartWorkerPool 启动工作池
	StartWorkerPool()
	// SendMsgToTaskQueue 向工作池发送请求
	SendMsgToTaskQueue(IRequest)
}
