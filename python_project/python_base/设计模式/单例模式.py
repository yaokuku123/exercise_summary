"""
单例模式

内容：保证一个类只有一个实例，并提供一个访问它的全局访问点

角色：
    单例（Singleton）

优点：
    1 对唯一实例的受控访问
    2 单例相当于全局变量，但防止了命名空间被污染

"""


#  单例（Singleton）
class Singleton(object):
    def __new__(cls, *args, **kwargs):
        if not hasattr(cls, '_instance'):
            cls._instance = super().__new__(cls)
        return cls._instance  # 具体的实例


class MyClass(Singleton):
    def __init__(self, name):
        self.name = name


a = MyClass("Burgess")
b = MyClass("Crystal")
print(a.name)
print(b.name)
print(id(a), id(b))
