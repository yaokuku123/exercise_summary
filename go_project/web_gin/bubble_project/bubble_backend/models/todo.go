package models

type Todo struct {
	ID     int    `json:"id"`
	Title  string `json:"title"`
	Status bool   `json:"status"`
}

// 定义和数据库关联的表名称
func (this *Todo) TableName() string {
	return "todo"
}
