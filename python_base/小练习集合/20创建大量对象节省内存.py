class Player1(object):
    def __init__(self, name, age):
        self.name = name
        self.age = age


class Player2(object):
    __slots__ = ['name', 'age']

    def __init__(self, name, age):
        self.name = name
        self.age = age


if __name__ == '__main__':
    p1 = Player1('yorick', 25)
    print(p1.name)
    # Python的动态性，会将动态赋值的属性保存到对象的__dict__属性字典中，该__dict__字典占有较高内存
    print(hasattr(p1, '__dict__'))  # True
    print(p1.__dict__)  # {'name': 'yorick', 'age': 25}

    p2 = Player2('yorick', 25)
    print(p2.name)
    # 采用__slot__的方式限制对象可以存储指定的属性，使得对象中不存在__dict__字典，从而降低内存的开销
    print(hasattr(p2, '__dict__'))  # False
