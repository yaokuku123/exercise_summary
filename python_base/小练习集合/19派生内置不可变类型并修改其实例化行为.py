# 案例：构建自己的tuple元组对象，使得可以过滤传入的可迭代对象，只保留其中int类型且为正数的元素
class MyTuple(tuple):
    # 在__init__函数之前调用__new__函数，首先依据要求修改实例化行为，再调用父类的__new__函数完成实例化对象创建
    def __new__(cls, iterable):
        f_it = (item for item in iterable if isinstance(item, int) and item > 0)
        return super().__new__(cls, f_it)


if __name__ == '__main__':
    my_tuple = MyTuple([-1, 1, 0, 10, 'hello', [1, 2, 3]])
    print(my_tuple)  # (1,10)
