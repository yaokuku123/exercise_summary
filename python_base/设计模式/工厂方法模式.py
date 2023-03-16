from abc import ABCMeta, abstractmethod
'''
Factory Method
'''
'''
工厂方法模式是简单工厂模式的衍生，解决了许多简单工厂模式的问题。
首先完全实现‘开－闭 原则’，实现了可扩展。其次更复杂的层次结构，可以应用于产品结果复杂的场合。 　　
工厂方法模式的对简单工厂模式进行了抽象。有一个抽象的Factory类（可以是抽象类和接口），这个类将不在负责具体的产品生产，
而是只制定一些规范，具体的生产工作由其子类去完成。在这个模式中，工厂类和产品类往往可以依次对应。
即一个抽象工厂对应一个抽象产品，一个具体工厂对应一个具体产品，这个具体的工厂就负责生产对应的产品。 　　

Shape（父类 or 基类）：提取出所有子类的重复方法代码
Circle（Shape子类 or 派生类）：作用为画圆形
Rectangle（Shape子类 or 派生类）：作用为画矩形
ShapeFactory（父类 or 基类）：提取出所有工厂子类的重复方法代码
CircleFactory（ShapeFactory or 派生类）：Circle工厂创建指定的Circle对象
RectangleFactory（ShapeFactory or 派生类）：Rectangle工厂创建指定的Rectangle对象
好处：增加一个具体类，只需要增加该类和其相对应的工厂，两个类，不需要修改工厂类。
缺点：由于每增加一个具体类就需要有一个对应的工厂类，导致类数量爆炸
'''


class Shape(metaclass=ABCMeta):
    '''
    父类
    '''

    @abstractmethod
    def draw(self):
        pass


class Circle(Shape):

    def __init__(self):
        self.shape_name = "Circle"

    def draw(self):
        print('draw circle')


class Rectangle(Shape):
    def __init__(self):
        self.shape_name = "Rectangle"

    def draw(self):
        print('draw Rectangle')


class ShapeFactory(metaclass=ABCMeta):
    '''接口基类'''
    @abstractmethod
    def create(self):
        '''把要创建的工厂对象装配进来'''
        pass


class CircleFactory(ShapeFactory):
    def create(self):
        return Circle()


class RectangleFactory(ShapeFactory):
    def create(self):
        return Rectangle()


shape_interface = CircleFactory()
obj = shape_interface.create()
obj.draw()

shape_interface2 = RectangleFactory()
obj2 = shape_interface2.create()
obj2.draw()