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

		// 用户操作
		v1.POST("user/register", api.UserRegisterHandler)
		v1.POST("user/login", api.UserLoginHandler)
		// 轮播图
		v1.GET("carousels", api.ListCarousels)
		authed := v1.Group("/") // 需要登录token验证的
		authed.Use(middleware.JWT())
		{
			// 用户操作
			authed.PUT("user", api.UserUpdate)
			authed.POST("avatar", api.AvatarUpdate)
			authed.POST("user/sending-email", api.SendEmail)
			authed.POST("user/valid-email/:emailToken", api.ValidEmail)

			// 显示金额
			authed.POST("money", api.ShowMoney)
		}
	}
	return r
}
