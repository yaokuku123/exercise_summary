package main

import "github.com/gin-gonic/gin"

func main() {
	// Gin 框架的HTTP服务启动
	r := gin.Default()
	r.GET("/", func(c *gin.Context) {
		c.JSON(200, gin.H{
			"message": "hello world",
		})
	})
	r.Run(":8080")
}
