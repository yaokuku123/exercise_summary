package main

import (
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/conf"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/dao"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/pkg/utils"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/routes"
)

func main() {
	conf.Init()
	utils.InitLog()
	utils.LogrusObj.Infoln("hello gin")
	dao.InitMySQL()
	r := routes.InitRouter()
	_ = r.Run(conf.HttpPort)
}
