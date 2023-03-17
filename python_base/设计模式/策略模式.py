from abc import ABCMeta, abstractmethod

'''
策略模式

内容：定义一系列的算法，把它们一个个封装起来，并且使它们可互相替换。本模式使得
算法可独立于使用它的客户而变化

角色：
    抽象策略（Strategy）
    具体策略（Concrete Strategy）
    上下文（Context）

优点：
    1 定义了一系列可重用的算法和行为
    2 消除了一些条件语句
    3 可以提供相同行为的不同实现
缺点：
    1 客户必须了解不同的策略

'''


# 抽象策略（Strategy）
class Strategy(metaclass=ABCMeta):
    @abstractmethod
    def execute(self, data):
        pass


# 具体策略（Concrete Strategy）
class FastStrategy(Strategy):
    def execute(self, data):
        print(f'fast strategy execute data:{data}')


# 具体策略（Concrete Strategy）
class SlowStrategy(Strategy):
    def execute(self, data):
        print(f'slow strategy execute data:{data}')


# 上下文（Context）
class Context(object):
    def __init__(self, data, strategy):
        self.data = data
        self.strategy = strategy

    def setStrategy(self, strategy):
        self.strategy = strategy

    def doStrategy(self):
        self.strategy.execute(self.data)


if __name__ == '__main__':
    context = Context('hello world', SlowStrategy())
    context.doStrategy()
    context.setStrategy(FastStrategy())
    context.doStrategy()
