import re
from functools import reduce


# 拆分含有多个分隔符的字符串

# 方法1：连续使用str.split()方法，每次处理一个分隔符的方式
def method1_1(s, seps):
    res = [s]
    for sep in seps:
        tmp = []
        # map函数遍历res列表中的所有数据（可能是二维列表，此处获取的是列表），split切割后，通过extend方法添加到tmp列表中
        list(map(lambda ss: tmp.extend(ss.split(sep)), res))
        res = tmp
    return res


def method1_2(s, seps):
    # reduce函数逐个遍历seps执行分割操作，初始化reduce第一个由[s]开始，
    # 对每次的列表首先通过map函数对列表中的元素进行分割，然后将结果sum成新的列表，再将这个结果作为reduce函数的下次输入通过分割符进行分割
    res = reduce(lambda l, sep: sum(map(lambda ss: ss.split(sep), l), []), seps, [s])
    return res


# 方法2：使用正则表达式re.split()方法，一次处理多个分隔符的方式
def method2(s, seps):
    res = re.split(f'[{seps}]+', s)
    return res


if __name__ == '__main__':
    s = 'ab;cd|efg|hi,jkl|mn\topq;rst,uvw\txyz'
    seps = ';,|\t'
    print(method1_1(s, seps))
    print(method1_2(s, seps))
    print(method2(s, seps))
