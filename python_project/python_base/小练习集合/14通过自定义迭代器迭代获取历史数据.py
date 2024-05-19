# 定义迭代器对象
class HistoryIterator(object):
    def __init__(self, history_info_list):
        self.history_info_list = history_info_list
        self.index = 0

    # 返回自己对象本身
    def __iter__(self):
        return self

    # 下次迭代获取的数据
    def __next__(self):
        # 迭代完毕，抛出停止迭代异常
        if self.index == len(self.history_info_list):
            raise StopIteration
        # 下一个迭代数据
        history_info = self.history_info_list[self.index]
        # 索引向后移动
        self.index += 1
        return history_info


# 定义可迭代对象
class HistoryIterable(object):
    def __init__(self, history_info_list):
        self.history_info_list = history_info_list

    # 返回值为重新创建的一个新的迭代器对象
    def __iter__(self):
        return HistoryIterator(self.history_info_list)


def show_history_info(h: HistoryIterable):
    for item in h:
        print(item)


if __name__ == '__main__':
    h = HistoryIterable(['yorick', 'tom', 'jerry'])
    show_history_info(h)
    print('-' * 20)
    show_history_info(h)
    print('-' * 20)

    hi = HistoryIterator(['yorick', 'tom', 'jerry'])
    show_history_info(hi)
    print('-' * 20)
    show_history_info(hi)  # 问题：迭代器只能被消耗一次，下次打印为空
