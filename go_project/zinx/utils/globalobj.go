package utils

import (
	"encoding/json"
	"io/ioutil"
)

type GlobalObj struct {
	// server config
	Name       string
	Version    string
	ServerIp   string
	ServerPort int

	// zinx config
	MaxPackageSize uint32
	MaxConn        int
}

func (this *GlobalObj) Reload() {
	dataFile, err := ioutil.ReadFile("config/zinx.json")
	if err != nil {
		panic(err)
	}
	// json解析
	err = json.Unmarshal(dataFile, &this)
	if err != nil {
		panic(err)
	}
}

var GlobalObject *GlobalObj

func init() {
	GlobalObject = &GlobalObj{
		Name:           "zinx server",
		Version:        "0.0.1",
		ServerIp:       "127.0.0.1",
		ServerPort:     8999,
		MaxPackageSize: 1024,
		MaxConn:        100,
	}
	// 加载配置文件
	GlobalObject.Reload()
}
