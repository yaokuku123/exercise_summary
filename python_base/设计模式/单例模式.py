"""
Singleton

实现__new__方法
并在将一个类的实例绑定到类变量_instance上,
如果cls._instance为None说明该类还没有实例化过,实例化该类,并返回
如果cls._instance不为None,直接返回cls._instance

通过执行结果我们可以看出：一个类永远只允许一个实例化对象，不管多少个进行实例化，都返回第一个实例化的对象
"""


class Singleton(object):
    def __new__(cls, *args, **kwargs):
        if not hasattr(cls, '_instance'):
            cls._instance = super().__new__(cls)
        return cls._instance  # 具体的实例


class MyClass(Singleton):
    def __init__(self, name):
        self.name = name


class Nana(Singleton):
    def __init__(self, name):
        self.name = name


a = MyClass("Burgess")
print(a.name)
b = MyClass("Crystal")
print(a.name)
print(b.name)
b.name = 'xx'
print(a.name)
print(b.name)
print(id(a))
print(id(b))
