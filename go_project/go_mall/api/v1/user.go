package v1

import (
	"github.com/gin-gonic/gin"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/pkg/utils"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/service"
	"net/http"
)

func UserRegisterHandler(c *gin.Context) {
	var userRegister service.UserService
	if err := c.ShouldBind(&userRegister); err != nil {
		c.JSON(http.StatusBadRequest, err)
		return
	}
	res := userRegister.Register(c.Request.Context())
	c.JSON(http.StatusOK, res)
}

func UserLoginHandler(c *gin.Context) {
	var userLogin service.UserService
	if err := c.ShouldBind(&userLogin); err != nil {
		c.JSON(http.StatusBadRequest, err)
		return
	}
	res := userLogin.Login(c.Request.Context())
	c.JSON(http.StatusOK, res)
}

func UserUpdate(c *gin.Context) {
	var userUpdate service.UserService
	claims, _ := utils.ParseToken(c.GetHeader("Authorization"))
	if err := c.ShouldBind(&userUpdate); err != nil {
		c.JSON(http.StatusBadRequest, err)
		return
	}
	res := userUpdate.UserUpdate(c.Request.Context(), claims.ID)
	c.JSON(http.StatusOK, res)
}
