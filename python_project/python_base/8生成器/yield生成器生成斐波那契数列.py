# 若函数中存在 yield 关键字，则当前函数变为生成器对象
def fibo_generator(n):
    i, a, b = 1, 0, 1
    for i in range(n):
        a, b = b, a + b
        print('start...')
        # 每次调用会在 yield 关键字位置暂停，下次调用会再次从 yield 关键字向下继续执行
        yield a
        print('end...')


if __name__ == '__main__':
    fn = fibo_generator(5)
    print(next(fn))
    print(next(fn))
    for val in fn:
        print(val)
