# 自定义上下午管理器对象
class MyFile(object):
    def __init__(self, filename, mode):
        self.filename = filename
        self.mode = mode

    # with自动调用类中的__enter__方法
    def __enter__(self):
        print('enter...')
        self.f = open(self.filename, self.mode, encoding='utf-8')
        return self.f

    # with结束或异常时自动调用__exit__方法
    def __exit__(self, exc_type, exc_val, exc_tb):
        print('exit...')
        self.f.close()


if __name__ == '__main__':
    # 写数据
    file = MyFile('./sava_data.txt', 'w')
    with file as f:
        f.write('hello world')

    # 读数据
    file = MyFile('./sava_data.txt', 'r')
    with file as f:
        content = f.read()
        print(content)
