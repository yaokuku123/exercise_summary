from abc import ABCMeta, abstractmethod

'''
组合模式

内容：将对象组合成树形结构以表示"部分-整体"的层次结构，组合模式使得用户对单个
对象和组合对象的使用具有一致性

角色：
    抽象组件（Component）
    叶子组件（Leaf）
    复合组件（Composite）
    客户端（Client）
    
场景：
    1 表示对象的"部分-整体"层次结构（特别：结构是递归的）
    2 希望用户忽略组合对象与单个对象的不同，用户统一地使用组合结构中的所有对象

优点：
    1 定义了包含基本对象和组合对象的层次结构
    2 简化客户端代码，即客户端可以一致地使用组合对象和单个对象
    3 更容易增加新类型的组件

'''


# 抽象组件（Component）
class Graphic(metaclass=ABCMeta):
    @abstractmethod
    def draw(self):
        pass


# 叶子组件（Leaf）
class Point(Graphic):
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __str__(self):
        return f'point => x:{self.x},y:{self.y}'

    def draw(self):
        print(self)


# 叶子组件（Leaf）
class Line(Graphic):
    def __init__(self, point1, point2):
        self.point1 = point1
        self.point2 = point2

    def __str__(self):
        return f'line => point1:{self.point1},point2:{self.point2}'

    def draw(self):
        print(self)


# 复合组件（Composite）
class Picture(Graphic):
    def __init__(self, iterable):
        self.children = []
        for item in iterable:
            self.children.append(item)

    def draw(self):
        print('-' * 10, 'picture start', '-' * 10)
        for item in self.children:
            item.draw()
        print('-' * 10, 'picture end', '-' * 10)


if __name__ == '__main__':
    p1 = Point(10, 20)
    line1 = Line(Point(1, 2), Point(3, 4))
    line2 = Line(Point(5, 6), Point(7, 8))
    pic1 = Picture([p1, line1, line2])
    pic1.draw()
    print('*' * 20)
    p2 = Point(30, 40)
    line3 = Line(Point(9, 10), Point(11, 12))
    pic2 = Picture([p2, line3])
    pic = Picture([pic1, pic2])
    pic.draw()
