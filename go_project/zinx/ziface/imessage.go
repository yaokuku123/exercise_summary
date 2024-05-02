package ziface

type IMessage interface {
	// getter
	GetMsgId() uint32
	GetDataLen() uint32
	GetData() []byte
	// setter
	SetMsgId(uint32)
	SetDataLen(uint32)
	SetData([]byte)
}
