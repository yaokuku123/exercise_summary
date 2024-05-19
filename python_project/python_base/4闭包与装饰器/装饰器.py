import time


# 装饰器函数，实际就是闭包
# 系统会将装饰的函数引用通过形参fn传入
def logging_info(fn):
    # 指定原函数的参数
    def inner(username, resource_id):
        # 前置处理
        print('start logging info...')
        # 调用被装饰函数
        fn(username, resource_id)
        # 后置处理
        print('end logging info')

    return inner


# 被装饰函数
# 添加装饰器
@logging_info
def read_data(username, resource_id):
    print(f'{username} reading data {resource_id}')


# 带传入参数的装饰器函数
def timeout_logging_info(timeout):
    def logging_info(fn):
        def inner(username, resource_id):
            # 前置处理
            print('start logging info...')
            print('waiting...')
            time.sleep(timeout)
            # 调用被装饰函数
            fn(username, resource_id)
            # 后置处理
            print('end logging info')

        return inner

    return logging_info


# 向装饰器中传入参数，得到的返回结果再将fn传入
# 1. a = timeout_logging_info(3) = logging_info，a指向logging_info函数
# 2. 装饰函数后，a = logging_info(read_data2) = inner，a最终指向了inner函数
# 3. 程序调用时，a = inner('yorick', 23)，调用装饰器函数，从而间接调用原函数
@timeout_logging_info(3)
def read_data2(username, resource_id):
    print(f'{username} reading data {resource_id}')


# 通用装饰器
def general_info(fn):
    # 接收任意类型的参数
    def inner(*args, **kwargs):
        print('inner before log...')
        # 拆包，参数传入原函数中
        fn(*args, **kwargs)
        print('inner after log...')

    return inner


@general_info
def read_data3(username, resource_id):
    print(f'{username} reading data {resource_id}')


if __name__ == '__main__':
    # read_data('yorick', 23)
    # read_data2('yorick', 23)
    read_data3('yorick', 23)
