from collections import OrderedDict
from itertools import islice
from random import shuffle


# 根据排名获取选手的成绩
def query_by_order(od, start, end=None):
    start -= 1
    if end is None:
        end = start + 1
    # OrderedDict不支持切片，使用islice函数可以模拟切片的操作
    return islice(od, start, end)


if __name__ == '__main__':
    # 随机生成abcdefgh集合的选手并打乱，并按照先后顺序作为排名顺序
    players = list('abcdefgh')
    shuffle(players)
    print(players)
    # 有序字典
    od = OrderedDict()
    for i, p in enumerate(players, 1):
        od[p] = i
    print(od)
    # 获取第2～3名的选手
    print(list(query_by_order(od, 2, 3)))
