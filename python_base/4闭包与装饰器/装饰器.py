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

# 添加装饰器
@logging_info
# 被装饰函数
def read_data(username, resource_id):
    print(f'{username} reading data {resource_id}')


if __name__ == '__main__':
    read_data('yorick', 23)
