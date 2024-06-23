package service

import (
	"github.com/yaokuku123/exercise_summary/go_project/web_gin/bubble_project/bubble_backend/dao"
	"github.com/yaokuku123/exercise_summary/go_project/web_gin/bubble_project/bubble_backend/models"
)

func CreateTodo(todo *models.Todo) error {
	return dao.DB.Create(todo).Error
}

func GetTodoList() ([]*models.Todo, error) {
	var todoList []*models.Todo
	if err := dao.DB.Find(&todoList).Error; err != nil {
		return nil, err
	}
	return todoList, nil
}

func UpdateTodo(id int, todo *models.Todo) error {
	var db_todo models.Todo
	dao.DB.Where("id = ?", id).First(&db_todo)
	db_todo.Status = todo.Status
	return dao.DB.Save(db_todo).Error
}

func DeleteTodo(id int) error {
	return dao.DB.Where("id = ?", id).Delete(&models.Todo{}).Error
}
