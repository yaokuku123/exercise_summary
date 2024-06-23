package service

import (
	"context"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/dao"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/model"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/pkg/e"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/pkg/utils"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/serializer"
)

type UserService struct {
	NickName string `form:"nick_name" json:"nick_name"`
	UserName string `form:"user_name" json:"user_name"`
	PassWord string `form:"password" json:"password"`
	Key      string `form:"key" json:"key"`
}

func (service *UserService) Register(ctx context.Context) serializer.Response {
	code := e.SUCCESS
	// 校验密钥长度是否符合要求
	if service.Key == "" || len(service.Key) != 16 {
		code = e.ERROR
		return serializer.Response{
			Status: code,
			Msg:    e.GetMsg(code),
			Error:  "密钥长度不足",
		}
	}
	// 初始化AES
	utils.Encrypt.SetKey(service.Key)
	// 获取userDao，绑定当前请求的ctx，拿到新的DB实例
	userDao := dao.NewUserDao(ctx)
	// 查看用户名是否存在
	_, exist, err := userDao.ExistOrNotByUserName(service.UserName)
	if err != nil {
		code = e.ErrorDatabase
		return serializer.Response{
			Status: code,
			Msg:    e.GetMsg(code),
		}
	}
	// 如果存在则无法创建，返回用户名重复错误
	if exist {
		code = e.ErrorExistUser
		return serializer.Response{
			Status: code,
			Msg:    e.GetMsg(code),
		}
	}
	// 构建用户
	user := &model.User{
		UserName: service.UserName,
		NickName: service.NickName,
		Status:   model.Active,
		Money:    utils.Encrypt.AesEncoding("10000"), // 初始金额
	}
	// 加密密码
	if err = user.SetPassword(service.PassWord); err != nil {
		code = e.ErrorFailEncryption
		return serializer.Response{
			Status: code,
			Msg:    e.GetMsg(code),
		}
	}
	user.Avatar = "avatar.JPG"
	// 创建用户
	err = userDao.CreateUser(user)
	if err != nil {
		code = e.ErrorDatabase
		return serializer.Response{
			Status: code,
			Msg:    e.GetMsg(code),
		}
	}
	// 返回结果
	return serializer.Response{
		Status: code,
		Msg:    e.GetMsg(code),
	}
}

func (service *UserService) Login(ctx context.Context) serializer.Response {
	code := e.SUCCESS
	userDao := dao.NewUserDao(ctx)
	// 查看用户是否已注册
	user, exist, err := userDao.ExistOrNotByUserName(service.UserName)
	if err != nil {
		code = e.ErrorDatabase
		return serializer.Response{
			Status: code,
			Msg:    e.GetMsg(code),
		}
	}
	if !exist {
		code = e.ErrorExistUser
		return serializer.Response{
			Status: code,
			Msg:    e.GetMsg(code),
		}
	}
	token, err := utils.GenerateToken(user.ID, user.UserName, 0)
	if err != nil {
		code := e.ErrorAuthToken
		return serializer.Response{
			Status: code,
			Msg:    e.GetMsg(code),
		}
	}
	return serializer.Response{
		Status: code,
		Msg:    e.GetMsg(code),
		Data: serializer.TokenData{
			User:  serializer.BuildUser(user),
			Token: token,
		},
	}
}
