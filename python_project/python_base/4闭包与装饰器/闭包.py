# 闭包规范：
# 1.有函数嵌套
# 2.有对外部函数的变量引用
# 3.有函数返回
def incr_count():
    count = 0

    def inner(num):
        # nonlocal获取最近层的变量，与global不同，global是获取全局变量
        # 内部函数存在对外部函数变量的引用，引用计数器+1，导致gc不会在外层函数结束后回收该函数空间
        nonlocal count
        count += num
        print(f'count: {count}')

    return inner


if __name__ == '__main__':
    fn = incr_count()
    fn(1)
    fn(2)
    fn(3)
