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

func AvatarUpdate(c *gin.Context) {
	file, _, _ := c.Request.FormFile("file")
	var userAvatarUpdate service.UserService
	claims, _ := utils.ParseToken(c.GetHeader("Authorization"))
	if err := c.ShouldBind(&userAvatarUpdate); err != nil {
		c.JSON(http.StatusBadRequest, err)
		return
	}
	res := userAvatarUpdate.AvatarUpdate(c.Request.Context(), claims.ID, file)
	c.JSON(http.StatusOK, res)
}

func SendEmail(c *gin.Context) {
	var userEmailService service.UserEmailService
	claims, _ := utils.ParseToken(c.GetHeader("Authorization"))
	if err := c.ShouldBind(&userEmailService); err != nil {
		c.JSON(http.StatusBadRequest, err)
		return
	}
	res := userEmailService.Send(c.Request.Context(), claims.ID)
	c.JSON(http.StatusOK, res)
}

func ValidEmail(c *gin.Context) {
	var userEmailService service.UserEmailService
	if err := c.ShouldBind(&userEmailService); err != nil {
		c.JSON(http.StatusBadRequest, err)
		return
	}
	emailToken := c.Param("emailToken")
	res := userEmailService.Valid(c.Request.Context(), emailToken)
	c.JSON(http.StatusOK, res)
}

func ShowMoney(c *gin.Context) {
	var moneyService service.MoneyService
	claims, _ := utils.ParseToken(c.GetHeader("Authorization"))
	if err := c.ShouldBind(&moneyService); err != nil {
		c.JSON(http.StatusBadRequest, err)
		return
	}
	res := moneyService.ShowMoney(c.Request.Context(), claims.ID)
	c.JSON(http.StatusOK, res)
}

func ListCarousels(c *gin.Context) {
	var carouselService service.CarouselService
	if err := c.ShouldBind(&carouselService); err != nil {
		c.JSON(http.StatusBadRequest, err)
		return
	}
	res := carouselService.ListCarousels(c.Request.Context())
	c.JSON(http.StatusOK, res)
}
