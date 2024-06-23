package middleware

import (
	"github.com/gin-gonic/gin"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/pkg/e"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/pkg/utils"
	"net/http"
	"time"
)

func JWT() gin.HandlerFunc {
	return func(c *gin.Context) {
		code := http.StatusOK
		token := c.GetHeader("Authorization")
		if token == "" {
			code = http.StatusUnauthorized
		} else {
			claims, err := utils.ParseToken(token)
			if err != nil {
				code = e.ErrorAuthCheckTokenFail
			} else if time.Now().Unix() > claims.ExpiresAt {
				code = e.ErrorAuthCheckTokenTimeout
			}
		}
		if code != http.StatusOK {
			c.JSON(code, gin.H{
				"status": code,
				"msg":    e.GetMsg(code),
			})
			c.Abort()
			return
		}
		c.Next()
	}
}
