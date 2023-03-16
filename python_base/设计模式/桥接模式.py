from abc import ABCMeta, abstractmethod

'''
桥接模式

内容：将一个事物的两个维度分离，使其都可以独立地变化

角色：
    抽象（Abstraction）
    细化抽象（Refined Abstraction）
    实现者（Implementor）
    具体实现者（Concrete Implementor）

场景：
    当事物有两个维度上的表现，两个维度都可能扩展时

优点：
    1 抽象和实现相互分离
    2 优秀的扩展能力

'''


# 抽象（Abstraction）
class Shape(metaclass=ABCMeta):
    def __init__(self, color):
        self.color = color

    @abstractmethod
    def draw(self):
        pass


# 实现者（Implementor）
class Color(metaclass=ABCMeta):
    @abstractmethod
    def paint(self, shape):
        pass


# 细化抽象（Refined Abstraction）
class Line(Shape):
    name = '直线'

    def draw(self):
        self.color.paint(self)


# 细化抽象（Refined Abstraction）
class Circle(Shape):
    name = '圆形'

    def draw(self):
        self.color.paint(self)


# 具体实现者（Concrete Implementor）
class RedColor(Color):
    def paint(self, shape):
        print(f'红色的{shape.name}')


# 具体实现者（Concrete Implementor）
class BlueColor(Color):
    def paint(self, shape):
        print(f'蓝色的{shape.name}')


if __name__ == '__main__':
    blue_circle = Circle(BlueColor())
    blue_circle.draw()
    print('-' * 20)
    red_circle = Circle(RedColor())
    red_circle.draw()
