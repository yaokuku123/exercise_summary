package utils

import (
	"github.com/yaokuku123/exercise_summary/go_project/go_mall/conf"
	"io/ioutil"
	"mime/multipart"
	"os"
	"strconv"
	"strings"
)

func UploadAvatarToLocalStatic(file multipart.File, uId uint, userName string) (string, error) {
	bId := strconv.Itoa(int(uId))
	uploadPath := strings.Join([]string{".", conf.AvatarPath, "user", bId}, "")
	if !DirExistOrNot(uploadPath) {
		CreateDir(uploadPath)
	}
	content, err := ioutil.ReadAll(file)
	if err != nil {
		return "", err
	}
	fileName := strings.Join([]string{uploadPath, "/", userName, ".jpg"}, "")
	err = ioutil.WriteFile(fileName, content, os.ModePerm)
	if err != nil {
		return "", err
	}
	return strings.Join([]string{"user", bId, "/", userName, ".jpg"}, ""), nil
}

func DirExistOrNot(path string) bool {
	s, err := os.Stat(path)
	if err != nil {
		return false
	}
	return s.IsDir()
}

func CreateDir(path string) bool {
	err := os.MkdirAll(path, os.ModePerm)
	if err != nil {
		return false
	}
	return true
}
