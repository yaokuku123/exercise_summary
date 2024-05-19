import socket

if __name__ == '__main__':
    # 1.创建socket对象
    # socket.AF_INET：表示Ipv4
    # socket.SOCK_STREAM：表示TCP协议
    tcp_client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    # 2.建立与server连接，传入元组 (ip, port)
    tcp_client_socket.connect(('localhost', 8080))
    while True:
        msg = input('input your msg: ')
        if msg == 'close':
            print('client close...')
            # 5.关闭连接
            tcp_client_socket.close()
            break
        # 3.发送数据，需要编码为byte流
        tcp_client_socket.send(msg.encode('utf-8'))
        # 4.接收数据，需要解码byte流
        content = tcp_client_socket.recv(1024).decode('utf-8')
        print(content)
