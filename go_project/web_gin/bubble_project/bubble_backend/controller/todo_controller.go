package controller

import (
	"fmt"
	"github.com/gin-gonic/gin"
	"github.com/yaokuku123/exercise_summary/go_project/web_gin/bubble_project/bubble_backend/models"
	"github.com/yaokuku123/exercise_summary/go_project/web_gin/bubble_project/bubble_backend/service"
	"net/http"
	"strconv"
)

// BubblePage 显示页面
func BubblePage(c *gin.Context) {
	c.HTML(http.StatusOK, "index.html", nil)
}

// CreateTodo 创建todo任务
func CreateTodo(c *gin.Context) {
	// 绑定前端传递的json=>todo对象
	var todo models.Todo
	err := c.BindJSON(&todo)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}
	// 添加到数据库
	err = service.CreateTodo(&todo)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}
	fmt.Printf("create todo task success，%#v\n", todo)
	c.JSON(http.StatusOK, todo)
}

// GetTodoList 获取todo任务列表
func GetTodoList(c *gin.Context) {
	todoList, err := service.GetTodoList()
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}
	c.JSON(http.StatusOK, todoList)
}

// UpdateTodo 更新todo任务
func UpdateTodo(c *gin.Context) {
	id_str := c.Param("id")
	// 绑定前端传递的json=>todo对象
	var todo models.Todo
	err := c.BindJSON(&todo)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}
	id, _ := strconv.ParseInt(id_str, 10, 64)
	err = service.UpdateTodo(int(id), &todo)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}
	c.JSON(http.StatusOK, todo)
}

// DeleteTodo 删除todo任务
func DeleteTodo(c *gin.Context) {
	id_str := c.Param("id")
	id, _ := strconv.ParseInt(id_str, 10, 64)
	err := service.DeleteTodo(int(id))
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}
	c.JSON(http.StatusOK, gin.H{"message": "todo delete success"})
}
