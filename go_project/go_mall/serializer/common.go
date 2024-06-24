package serializer

// Response 基础序列化器
type Response struct {
	Status int         `json:"status"`
	Data   interface{} `json:"data"`
	Msg    string      `json:"msg"`
	Error  string      `json:"error"`
}

// TokenData 带有token的Data结构
type TokenData struct {
	User  interface{} `json:"user"`
	Token string      `json:"token"`
}

type DataList struct {
	Item  interface{} `json:"item"`
	Total int         `json:"total"`
}

func BuildListResponse(list interface{}, total int) Response {
	return Response{
		Status: 200,
		Data: DataList{
			Item:  list,
			Total: total,
		},
		Msg: "ok",
	}
}
