package dao

import (
	"context"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/model"
	"gorm.io/gorm"
)

type NoticeDao struct {
	DB *gorm.DB
}

func NewNoticeDao(ctx context.Context) *NoticeDao {
	return &NoticeDao{DB: NewDBClient(ctx)}
}

func (dao *NoticeDao) GetNoticeById(id uint) (notice *model.Notice, err error) {
	err = dao.DB.Model(&model.Notice{}).Where("id=?", id).First(&notice).Error
	return
}
