package routers

import (
	"github.com/gin-gonic/gin"
	"github.com/yaokuku123/exercise_summary/go_project/web_gin/bubble_project/bubble_backend/controller"
)

func InitRouter() *gin.Engine {
	r := gin.Default()
	r.Static("/static", "./static")
	r.LoadHTMLGlob("./templates/*")
	group := r.Group("v1")
	{
		// 显示页面
		group.GET("/", controller.BubblePage)
		// 创建todo任务
		group.POST("/todo", controller.CreateTodo)
		// 获取todo任务列表
		group.GET("/todo", controller.GetTodoList)
		// 更新todo任务
		group.PUT("/todo/:id", controller.UpdateTodo)
		// 删除todo任务
		group.DELETE("/todo/:id", controller.DeleteTodo)
	}
	return r
}
