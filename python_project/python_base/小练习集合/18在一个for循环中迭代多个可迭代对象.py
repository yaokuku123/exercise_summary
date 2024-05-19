from random import randint
from itertools import chain
from functools import reduce


# 并行案例：通过并行计算学生成绩总和
# 方法1：采用zip的方式将多个可迭代对象集成至元组中迭代
def parallel_zip(*args):
    # method 1 采用for循环遍历zip函数返回的迭代器
    # sum_l = []
    # for c, e, m in zip(*args):
    #     sum_l.append(c + e + m)
    # return sum_l

    # method2 列表解析器
    return [sum(v) for v in zip(*args)]


# 方法2：采用map的方式
def parallel_map(*args):
    # *score将参数封装为元组，直接将score该元组传递给sum函数计算总和
    return map(lambda *score: sum(score), *args)


# 串行案例1：通过chain实现过滤分数高于90的人数
def serial_chain1(*args):
    # *args将传递的参数元组打散，传递给chain函数串行合并每个列表
    # 遍历该合并的列表，判别成绩分数是否大于90分，将目标分数保留，最后计算该结果列表的长度即为高于90分的人数
    return len([c for c in chain(*args) if c > 90])


# 串行案例2：通过chain实现对多个分隔符的拆分
def serial_chain2():
    s = 'ab;cd|efg|hi,jkl|mn\topq;rst,uvw\txyz'
    seps = ';,|\t'
    # reduce函数对[s]，依次遍历采用seps中的分隔符进行切分
    # map函数遍历l列表中每个字符串，采用sep分隔符进行切分
    # chain函数将最终切分得到的二维列表中每个一维列表进行串行合并，采用*将map返回的结果打散
    return list(reduce(lambda l, sep: chain(*map(lambda ss: ss.split(sep), l)), seps, [s]))


if __name__ == '__main__':
    chinese = [randint(60, 100) for _ in range(10)]
    english = [randint(60, 100) for _ in range(10)]
    math = [randint(60, 100) for _ in range(10)]
    print(chinese)
    print(english)
    print(math)
    # 并行迭代
    print(parallel_zip(chinese, english, math))
    print(list(parallel_map(chinese, english, math)))

    # 串行迭代
    print(serial_chain1(chinese, english, math))
    print(serial_chain2())
