package ziface

type IMsgHandler interface {
	// DoMsgHandler 执行路由处理器
	DoMsgHandler(IRequest)
	// AddRouter 添加路由处理器
	AddRouter(uint32, IRouter)
}
