from functools import reduce


# 方法1：迭代列表，使用+操作依次拼接字符串，该方案会使得俩俩拼接过程中，创建很多临时字符串，效率和开销均需要优化
def method1_1(l):
    res = ''
    for x in l:
        res += x
    return res


def method1_2(l):
    # 使用reduce函数逐个拼接字符串，调用str.__add__可以接收两个字符串参数并将其拼接
    return reduce(str.__add__, l)


# 方法2：使用字符串的join方法，直接分配空间，更高效的拼接字符串
def method2(l):
    return ''.join(l)


if __name__ == '__main__':
    l = ['<hello>', '<java>', '<python>']
    print(method1_1(l))
    print(method1_2(l))
    print(method2(l))
