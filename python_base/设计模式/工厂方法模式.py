from abc import ABCMeta, abstractmethod

'''
工厂方法模式

内容：定义一个用于创建对象的接口（工厂接口），让子类决定实例化哪一个产品类

角色：
    抽象工厂角色（Creator）
    具体工厂角色（Concrete Creator）
    抽象产品角色（Product）
    具体产品角色（Concrete Product）

优点：
    1 每个具体产品都对应一个具体工厂类，不需要修改工厂类代码
    2 隐藏类对象创建的实现细节
缺点：
    1 每增加一个具体产品类，就必须增加一个相应的具体工厂类（类数量爆炸）

'''


# 抽象产品角色（Product）
class Shape(metaclass=ABCMeta):
    '''
    父类
    '''

    @abstractmethod
    def draw(self):
        pass


# 具体产品角色（Concrete Product）
class Circle(Shape):

    def __init__(self):
        self.shape_name = "Circle"

    def draw(self):
        print('draw circle')


# 具体产品角色（Concrete Product）
class Rectangle(Shape):
    def __init__(self):
        self.shape_name = "Rectangle"

    def draw(self):
        print('draw Rectangle')


# 抽象工厂角色（Creator）
class ShapeFactory(metaclass=ABCMeta):
    '''接口基类'''

    @abstractmethod
    def create(self):
        '''把要创建的工厂对象装配进来'''
        pass


# 具体工厂角色（Concrete Creator）
class CircleFactory(ShapeFactory):
    def create(self):
        return Circle()


# 具体工厂角色（Concrete Creator）
class RectangleFactory(ShapeFactory):
    def create(self):
        return Rectangle()


shape_interface = CircleFactory()
obj = shape_interface.create()
obj.draw()

shape_interface2 = RectangleFactory()
obj2 = shape_interface2.create()
obj2.draw()
