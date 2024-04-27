# Go Mod 模式构建步骤

1. 环境变量中加入对mod模式的支持
```shell
# Go
export GOPATH=/Users/yorick/Documents/study/code/go-project
export GOBIN=$GOPATH/bin
export PATH=$PATH:$GOBIN
export PATH=$PATH:/Users/yorick/Documents/study/code/env-dep/protoc/bin
export GO111MODULE=on
export GOPROXY=https://goproxy.cn,direct
# Go END
```

2. 创建go mod模式的工程，无需放在$GOPATH/src中

```shell
mkdir -p github.com/yaokuku123/go_module_test
cd github.com/yaokuku123/go_module_test
# 初始化工程
go mod init
```

3. 创建main.go文件用于测试代码
```go
package main

import (
	"fmt"
	"github.com/aceld/zinx/ziface"
	"github.com/aceld/zinx/znet"
)

// PingRouter MsgId=1
type PingRouter struct {
	znet.BaseRouter
}

//Ping Handle MsgId=1
func (r *PingRouter) Handle(request ziface.IRequest) {
	//read client data
	fmt.Println("recv from client : msgId=", request.GetMsgID(), ", data=", string(request.GetData()))
}

func main() {
	//1 Create a server service
	s := znet.NewServer()

	//2 configure routing
	s.AddRouter(1, &PingRouter{})

	//3 start service
	s.Serve()
}
```

4. 下载go代码的依赖
```shell
go mod tidy
# or
go get github.com/aceld/zinx/ziface
go get github.com/aceld/zinx/znet
```

5. 启动服务
```shell
go run main.go
```