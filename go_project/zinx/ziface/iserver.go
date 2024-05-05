package ziface

type IServer interface {
	// 启动服务器
	Start()
	// 关闭服务器
	Stop()
	// 运行服务器
	Serve()
	// 添加路由
	AddRouter(uint32, IRouter)
	// 获取链接管理器
	GetConnMgr() IConnManager
	// 设置链接创建后的回调
	SetOnConnStart(func(IConnection))
	// 设置链接结束前的回调
	SetOnConnStop(func(IConnection))
	// 调用创建链接后的回调函数
	CallOnConnStart(IConnection)
	// 调用链接结束前的回调函数
	CallOnConnStop(IConnection)
}
