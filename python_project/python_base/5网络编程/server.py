import socket


# 面向对象的方式创建server
class WebServer(object):
    # 初始化server的socket
    def __init__(self):
        # 1.创建socket对象
        self.tcp_server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        # 2.设置端口复用
        self.tcp_server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, True)
        # 3.绑定ip和port，元组的形式入参
        self.tcp_server_socket.bind(('localhost', 8080))
        # 4.启动server监听，可同时接收128个客户端的连接
        self.tcp_server_socket.listen(128)

    # 具体处理请求，解耦
    def request_handler(self, new_socket, ip_port):
        while True:
            # 获取客户端的发送内容
            content = new_socket.recv(1024).decode('utf-8')
            # 如果客户端关闭socket，则释放与该客户端的socket连接
            if not content:
                print('client close...')
                new_socket.close()
                break
            print(f'server recv {ip_port} content: {content}')
            # 向该客户端发送数据
            new_socket.send('server recv your msg!'.encode('utf-8'))

    # 启动server，单线程版本仅支持在同一时间处理一个客户端的请求
    def start(self):
        while True:
            # 5.接收client的socket连接
            new_socket, ip_port = self.tcp_server_socket.accept()
            # 处理请求
            self.request_handler(new_socket, ip_port)


if __name__ == '__main__':
    web_server = WebServer()
    web_server.start()
