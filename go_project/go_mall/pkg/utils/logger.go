package utils

import (
	"github.com/sirupsen/logrus"
	"os"
	"path"
	"time"
)

var LogrusObj *logrus.Logger

func InitLog() {
	src, _ := setOutputFile()
	if LogrusObj != nil {
		LogrusObj.Out = src
		return
	}
	logger := logrus.New()
	logger.Out = src
	logger.SetLevel(logrus.DebugLevel)
	logger.SetFormatter(&logrus.TextFormatter{TimestampFormat: "2006-01-02 15:04:05"})
	LogrusObj = logger
}

func setOutputFile() (*os.File, error) {
	now := time.Now()
	logFilePath := ""
	if dir, err := os.Getwd(); err == nil {
		logFilePath = dir + "/logs/"
	}
	_, err := os.Stat(logFilePath)
	if os.IsNotExist(err) {
		err := os.MkdirAll(logFilePath, os.ModePerm)
		if err != nil {
			return nil, err
		}
	}
	loggerName := now.Format("2006-01-02") + ".log"
	// 日志文件
	fileName := path.Join(logFilePath, loggerName)
	if _, err := os.Stat(fileName); err != nil {
		if _, err := os.Create(fileName); err != nil {
			return nil, err
		}
	}
	file, err := os.OpenFile(fileName, os.O_APPEND|os.O_WRONLY, os.ModeAppend)
	if err != nil {
		return nil, err
	}
	return file, nil
}
