package dao

import (
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
)

var (
	DB *gorm.DB
)

// 初始化DB对象
func init() {
	dst := "root:199748@tcp(127.0.0.1:3306)/test_db?charset=utf8mb4&parseTime=True&loc=Local"
	db, err := gorm.Open(mysql.Open(dst), &gorm.Config{})
	if err != nil {
		panic(err)
	}
	DB = db
}
