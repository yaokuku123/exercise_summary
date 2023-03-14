from itertools import islice


# 模拟实现islice的函数功能
def my_islice(iterable, start, end, step=1):
    tmp = 0
    for i, v in enumerate(iterable):
        # 超过边界break
        if i >= end:
            break
        # 达到获取值的开始位置
        if i >= start:
            # 步进到达节点
            if tmp == 0:
                tmp = step
                yield v
            tmp -= 1


if __name__ == '__main__':
    l = [1, 2, 3, 4, 5]
    # 调用islice函数完成对可迭代对象的切片操作
    print(list(islice(l, 1, 4, 2)))
    # 调用模拟的islice函数完成对可迭代对象的切片操作
    print(list(my_islice(l, 1, 4, 2)))
