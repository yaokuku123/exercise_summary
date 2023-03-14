from random import randint, sample
from functools import reduce


# 找到多个字典的公共键

# 方法1，获取第一个字典的元素，看是否在剩余的字典的键集合中出现过
def method1(dl: list):
    # 从第一个字典dl[0]中循环获取元素，map函数看其余字典dl[1:]中是否有该元素并返回是否有元素的结果生成器，all函数判别若全为True则返回True
    res = [k for k in dl[0] if all(map(lambda d: k in d, dl[1:]))]
    print(res)


# 方法2，使用map函数获取每个字典的键集合，再使用reduce函数逐个求每个键集合的交集，得到公共键
def method2(dl: dict):
    # map函数获取列表中字典的键集合，reduce函数逐步对每个字典的键集合求交集，结果为所有字典键的交集结果
    res = reduce(lambda a, b: a & b, map(dict.keys, dl))
    print(res)


if __name__ == '__main__':
    # 创建三个字典放入列表中，其中每个字典的key是从abcdefg集合中随意挑选[3，6]个字符，字典值范围为[2,4]
    dl = []
    for i in range(3):
        dl.append({k: randint(2, 4) for k in sample('abcdefg', randint(3, 6))})
    print(dl)
    method1(dl)
    method2(dl)
