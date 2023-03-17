from abc import ABCMeta, abstractmethod
from time import sleep

'''
模板模式

内容：定义一个操作中的算法估计，而将一些具体步骤延迟到子类中实现。模板方法使得
子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤

角色：
    1 抽象类（Abstract Class）：定义抽象的原子操作（钩子），实现模板方法作为算法估计
    2 具体类（Concrete Class）：实现原子操作

场景：
    1 一次性实现一个算法的不变部分
    2 各个子类中的公共行为应该被提取出来并集中到一个公共父类中以避免代码重复
    3 控制子类扩展

'''


# 抽象类（Abstract Class）
class Window(metaclass=ABCMeta):
    @abstractmethod
    def start(self):
        pass

    @abstractmethod
    def repaint(self):
        pass

    @abstractmethod
    def stop(self):
        pass

    def run(self):
        self.start()
        while True:
            try:
                self.repaint()
                sleep(1)
            except KeyboardInterrupt:
                break
        self.stop()


# 具体类（Concrete Class）
class MyWindow(Window):
    def __init__(self, data):
        self.data = data

    def start(self):
        print('my window start')

    def repaint(self):
        print(f'{self.data}')

    def stop(self):
        print('my window stop')


if __name__ == '__main__':
    my_window = MyWindow('hello world')
    my_window.run()
