package main

import (
	_ "github.com/yaokuku123/exercise_summary/go_project/web_gin/bubble_project/bubble_backend/dao"
	"github.com/yaokuku123/exercise_summary/go_project/web_gin/bubble_project/bubble_backend/routers"
)

func main() {
	r := routers.InitRouter()
	err := r.Run(":8080")
	if err != nil {
		panic(err)
	}
}
