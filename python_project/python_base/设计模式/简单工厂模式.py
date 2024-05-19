from abc import ABCMeta, abstractmethod

'''
简单工厂模式

内容：不直接向客户端暴露对象的实现细节，而是通过一个工厂类来负责创建产品类的实例

角色：
    工厂角色（Creator）
    抽象产品角色（Product）
    具体产品角色（Concrete Product）

优点：
    1 隐藏来对象创建的实现细节
    2 客户端不需要修改代码
缺点
    1 违反来单一职责原则，将创建逻辑集中到了一个工厂类里
    2 当添加新产品时，需要修改工厂类代码，违反类开闭原则

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
    '''
    Shape子类
    '''

    def draw(self):
        print('draw circle')

# 具体产品角色（Concrete Product）
class Rectangle(Shape):
    '''
    Shape的子类
    '''

    def draw(self):
        print('draw Rectangle')


# 工厂角色（Creator）
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
