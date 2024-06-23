package dao

import (
	"context"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/model"
	"gorm.io/gorm"
)

type UserDao struct {
	DB *gorm.DB
}

func NewUserDao(ctx context.Context) *UserDao {
	return &UserDao{DB: NewDBClient(ctx)}
}

func (dao *UserDao) ExistOrNotByUserName(username string) (user *model.User, exist bool, err error) {
	var count int64
	err = dao.DB.Model(&model.User{}).Where("user_name = ?", username).Find(&user).Count(&count).Error
	if err != nil {
		return nil, false, err
	}
	if count == 0 {
		return nil, false, nil
	}
	return user, true, nil
}

func (dao *UserDao) CreateUser(user *model.User) error {
	return dao.DB.Model(&model.User{}).Create(&user).Error
}

func (dao *UserDao) GetUserById(uId uint) (user *model.User, err error) {
	err = dao.DB.Model(&model.User{}).Where("id = ?", uId).First(&user).Error
	return
}

func (dao *UserDao) UpdateUserById(uId uint, user *model.User) error {
	return dao.DB.Model(&model.User{}).Where("id = ?", uId).Updates(&user).Error
}
