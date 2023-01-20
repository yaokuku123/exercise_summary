from collections import Counter
import heapq
from random import randint


# 统计序列中元素的频度

# 方法1：将序列转换为字典{元素：频度}，根据字典中值频度进行由高到低的排序，找到频度最大的n个值
def method1(data):
    # 1.将列表转换为字典的键，通过dict.fromkeys函数可以将数据转为字典的键，0表示默认赋值
    freq_dict = dict.fromkeys(data, 0)
    # 2.统计元素的频度
    for x in data:
        freq_dict[x] += 1
    # 方法1，采用sorted函数全排序
    # 3.根据频度大小对字典排序，取前3个频度的数据作为结果
    # 使用生成器解析相较于列表解析可以更节省内存空间
    res1 = sorted(((v, k) for k, v in freq_dict.items()), reverse=True)[:3]
    print(res1)
    # 方法2，采用堆的方式，效率更高，无需全部排序
    res2 = heapq.nlargest(3, ((v, k) for k, v in freq_dict.items()))
    print(res2)


# 方法2：使用标准库collections中的Counter对象
def method2(data):
    # 创建Counter对象
    counter = Counter(data)
    # 获取频率前3的元素
    res = counter.most_common(3)
    print(res)


if __name__ == '__main__':
    # 随机生成30个值在范围[0,20]的元素列表
    data = [randint(0, 20) for _ in range(30)]
    print(data)
    method1(data)
    method2(data)
