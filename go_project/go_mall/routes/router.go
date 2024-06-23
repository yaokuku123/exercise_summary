package routes

import (
	"github.com/gin-gonic/gin"
	api "github.com/yaokuku123/exercise_summary/go_project/go_mall/api/v1"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/middleware"
	"net/http"
)

func InitRouter() *gin.Engine {
	r := gin.Default()
	r.Use(middleware.Cors())
	r.StaticFS("/static", http.Dir("./static"))
	v1 := r.Group("api/v1")
	{
		v1.GET("ping", func(c *gin.Context) {
			c.JSON(http.StatusOK, "success")
		})

		v1.POST("user/register", api.UserRegisterHandler)
	}
	return r
}
