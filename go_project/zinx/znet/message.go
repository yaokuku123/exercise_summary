package znet

type Message struct {
	Id      uint32
	DataLen uint32
	Data    []byte
}

func NewMessage(id uint32, data []byte) *Message {
	return &Message{
		Id:      id,
		DataLen: uint32(len(data)),
		Data:    data,
	}
}

func (this *Message) GetMsgId() uint32 {
	return this.Id
}

func (this *Message) GetDataLen() uint32 {
	return this.DataLen
}

func (this *Message) GetData() []byte {
	return this.Data
}

func (this *Message) SetMsgId(id uint32) {
	this.Id = id
}

func (this *Message) SetDataLen(dataLen uint32) {
	this.DataLen = dataLen
}

func (this *Message) SetData(data []byte) {
	this.Data = data
}
