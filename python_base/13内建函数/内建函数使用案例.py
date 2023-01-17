# map() 映射数据计算
from functools import reduce


def test_map():
    # 根据第一个参数规定的方法，使用第二个参数中的数据逐一套用方法，并返回生成器
    iter_map1 = map(lambda x: x * 2, [1, 3, 5])
    for val in iter_map1:
        print(val)

    # 后两个参数分别作为x，y的输入取值，使用第一个参数方法计算，并返回生成器。
    # 其中，后面的参数只要是可迭代的即可，不仅可以传列表
    iter_map2 = map(lambda x, y: x + y, [1, 2, 4], (3, 4, 5))
    for val in iter_map2:
        print(val)


# filter() 过滤数据
def test_filter():
    # 当且仅当符合第一个参数方法的数据才会被返回，并返回的是迭代器。
    # 例子中，只有为偶数的数据才可以通过筛选被返回
    iter_filter = filter(lambda x: x % 2 == 0, [1, 2, 3, 4, 5])
    for val in iter_filter:
        print(val)


# reduce() 计算参数序列
def test_reduce():
    # reduce会将之前计算的结果作为x和下一个数据按照指定的方法计算，返回最后的结果数值
    res = reduce(lambda x, y: x + y, [1, 2, 3, 4])
    print(res)

    # 后面若没跟参数，则第一个参数作为开始的x，若后面跟着参数，则将其作为第一个参数作为开始的x
    res2 = reduce(lambda x, y: x + y, [1, 2, 3, 4], 5)
    print(res2)


if __name__ == '__main__':
    # test_map()
    # test_filter()
    test_reduce()
