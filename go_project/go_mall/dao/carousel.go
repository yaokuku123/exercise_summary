package dao

import (
	"context"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/model"
	"gorm.io/gorm"
)

type CarouselDao struct {
	DB *gorm.DB
}

func NewCarouselDao(ctx context.Context) *CarouselDao {
	return &CarouselDao{DB: NewDBClient(ctx)}
}

func (dao *CarouselDao) ListCarousel() (carousels []*model.Carousel, err error) {
	err = dao.DB.Model(&model.Carousel{}).Find(&carousels).Error
	return
}
