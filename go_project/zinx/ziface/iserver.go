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
}
