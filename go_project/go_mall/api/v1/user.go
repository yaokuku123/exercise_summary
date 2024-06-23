package v1

import (
	"github.com/gin-gonic/gin"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/service"
	"net/http"
)

func UserRegisterHandler(c *gin.Context) {
	var userService service.UserService
	if err := c.ShouldBind(&userService); err != nil {
		c.JSON(http.StatusBadRequest, "error")
		return
	}
	res := userService.Register(c.Request.Context())
	c.JSON(http.StatusOK, res)
}
