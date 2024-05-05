package ziface

type IConnManager interface {
	// 添加链接
	Add(IConnection)
	// 删除链接
	Remove(IConnection)
	// 获取链接
	GetConn(uint32) (IConnection, error)
	// 获取链接管理器中的链接个数
	Len() int
	// 清空全部管理器中的链接
	ClearConn()
}
