package service

import (
	"context"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/dao"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/pkg/e"
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/serializer"
)

type CarouselService struct {
}

func (service *CarouselService) ListCarousels(ctx context.Context) serializer.Response {
	code := e.SUCCESS
	carouselDao := dao.NewCarouselDao(ctx)
	carousels, err := carouselDao.ListCarousel()
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
		Data:   serializer.BuildListResponse(serializer.BuildCarousels(carousels), len(carousels)),
	}
}
