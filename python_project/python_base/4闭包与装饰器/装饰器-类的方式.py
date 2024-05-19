import time


# 定义装饰器类
class MyDecorator(object):
    def __init__(self, fn):
        print('MyDecorator init...')
        self.__fn = fn

    def __call__(self, *args, **kwargs):
        print('before fn...')
        self.__fn(*args, **kwargs)
        print('after fn...')


# 1. a = MyDecorator(read_data) a指向MyDecorator的对象，并将函数传入构造方法
# 2. 程序调用时，a = 实例对象('yorick', 23) a指向实例对象，实例对象() 调用本身的__call__方法
@MyDecorator
def read_data(username, resource_id):
    print(f'{username} reading data {resource_id}')


# 定义装饰器类，可以传入参数
class MyDecorator2(object):
    def __init__(self, timeout):
        print('MyDecorator2 init...')
        self.__timeout = timeout

    def __call__(self, fn):
        print('before call..')
        self.__fn = fn
        print('after call...')
        return self.class_inner

    def class_inner(self, *args, **kwargs):
        print('before fn...')
        print('waiting...')
        time.sleep(self.__timeout)
        self.__fn(*args, **kwargs)
        print('after fn...')


# 1. a = MyDecorator(timeout) a指向MyDecorator的对象，并将参数传入构造方法
# 2. 装饰函数后，a = 实例对象(fn) = self.class_inner a指向实例对象，实例对象() 调用本身的__call__方法，传入当前函数引用，返回self.class_inner方法
# 3. 程序调用时，a = self.class_inner('yorick', 23)，调用装饰器函数，从而间接调用原函数
@MyDecorator2(3)
def read_data2(username, resource_id):
    print(f'{username} reading data {resource_id}')


if __name__ == '__main__':
    # read_data('yorick', 23)
    read_data2('yorick', 23)
