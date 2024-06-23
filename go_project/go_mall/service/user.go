package service

import (
	"context"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/conf"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/dao"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/model"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/pkg/e"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/pkg/utils"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/serializer"
	"gopkg.in/mail.v2"
	"mime/multipart"
	"strconv"
	"strings"
)

type UserService struct {
	NickName string `form:"nick_name" json:"nick_name"`
	UserName string `form:"user_name" json:"user_name"`
	PassWord string `form:"password" json:"password"`
	Key      string `form:"key" json:"key"`
}

type UserEmailService struct {
	Email         string `form:"email" json:"email"`
	Password      string `form:"password" json:"password"`
	OperationType uint   `form:"operation_type" json:"operation_type"` // OpertionType 1:绑定邮箱 2：解绑邮箱 3：改密码
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
		code = e.ErrorNotExistAddress
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

func (service *UserService) UserUpdate(ctx context.Context, uId uint) serializer.Response {
	code := e.SUCCESS
	userDao := dao.NewUserDao(ctx)
	user, err := userDao.GetUserById(uId)
	if err != nil {
		code = e.ErrorDatabase
		return serializer.Response{
			Status: code,
			Msg:    e.GetMsg(code),
		}
	}
	if service.NickName != "" {
		user.NickName = service.NickName
	}
	err = userDao.UpdateUserById(user.ID, user)
	if err != nil {
		code = e.ErrorDatabase
		return serializer.Response{
			Status: code,
			Msg:    e.GetMsg(code),
		}
	}
	return serializer.Response{
		Status: code,
		Msg:    e.GetMsg(code),
		Data:   serializer.BuildUser(user),
	}
}

func (service *UserService) AvatarUpdate(ctx context.Context, uId uint, file multipart.File) serializer.Response {
	code := e.SUCCESS
	userDao := dao.NewUserDao(ctx)
	user, err := userDao.GetUserById(uId)
	if err != nil {
		code = e.ErrorDatabase
		return serializer.Response{
			Status: code,
			Msg:    e.GetMsg(code),
		}
	}
	path, err := utils.UploadAvatarToLocalStatic(file, uId, user.UserName)
	if err != nil {
		code = e.ErrorUploadFile
		return serializer.Response{
			Status: code,
			Msg:    e.GetMsg(code),
		}
	}
	user.Avatar = path
	err = userDao.UpdateUserById(user.ID, user)
	if err != nil {
		code = e.ErrorDatabase
		return serializer.Response{
			Status: code,
			Msg:    e.GetMsg(code),
		}
	}
	return serializer.Response{
		Status: code,
		Msg:    e.GetMsg(code),
		Data:   serializer.BuildUser(user),
	}
}

func (service *UserEmailService) Send(ctx context.Context, uId uint) serializer.Response {
	code := e.SUCCESS
	token, err := utils.EmailGenerateToken(uId, service.OperationType, service.Email, service.Password)
	if err != nil {
		code = e.ErrorAuthToken
		return serializer.Response{
			Status: code,
			Msg:    e.GetMsg(code),
		}
	}
	noticeDao := dao.NewNoticeDao(ctx)
	notice, err := noticeDao.GetNoticeById(service.OperationType)
	if err != nil {
		code = e.ErrorDatabase
		return serializer.Response{
			Status: code,
			Msg:    e.GetMsg(code),
		}
	}
	address := conf.ValidEmail + token
	mailStr := notice.Text
	mailText := strings.Replace(mailStr, "Email", address, -1)
	m := mail.NewMessage()
	m.SetHeader("From", conf.SmtpEmail)
	m.SetHeader("To", service.Email)
	m.SetHeader("Subject", "yorick")
	m.SetBody("text/html", mailText)
	smptPort, _ := strconv.ParseInt(conf.SmtpPort, 10, 64)
	d := mail.NewDialer(conf.SmtpHost, int(smptPort), conf.SmtpEmail, conf.SmtpPass)
	err = d.DialAndSend(m)
	if err != nil {
		code = e.ErrorSendEmail
		return serializer.Response{
			Status: code,
			Msg:    e.GetMsg(code),
		}
	}
	return serializer.Response{
		Status: code,
		Msg:    e.GetMsg(code),
	}
}
