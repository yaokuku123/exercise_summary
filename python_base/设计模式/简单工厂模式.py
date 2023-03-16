from abc import ABCMeta, abstractmethod

'''
Simple Factory Method

Shape（父类 or 基类）：提取出所有子类的重复方法代码
Circle（Shape子类 or 派生类）：作用为画圆形
Rectangle（Shape子类 or 派生类）：作用为画矩形
ShapeFactory（新式类）：该类作用为用户可根据该类对象创建指定的Shape子类对象（Circle or Rectangle）
优点：客户端不需要修改代码。
缺点： 当需要增加新的运算类的时候，要修改工厂类，违反了开闭原则。
'''


class Shape(metaclass=ABCMeta):
    '''
    父类
    '''

    @abstractmethod
    def draw(self):
        pass


class Circle(Shape):
    '''
    Shape子类
    '''

    def draw(self):
        print('draw circle')


class Rectangle(Shape):
    '''
    Shape的子类
    '''

    def draw(self):
        print('draw Rectangle')


class ShapeFactory(object):
    '''
    工厂模式：暴露给用户去调用的，
    用户可通过该类进行选择Shape的子类进行实例化
    '''

    def create(self, shape):
        if shape == 'Circle':
            return Circle()
        elif shape == 'Rectangle':
            return Rectangle()
        else:
            return None


fac = ShapeFactory()  # 实例化工厂类
obj = fac.create('Circle')  # 实例化Shape的Circle子类
obj.draw()
