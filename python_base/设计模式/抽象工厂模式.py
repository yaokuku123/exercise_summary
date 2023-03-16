from abc import ABCMeta, abstractmethod

'''
Abstract Factory

AbstractFactory（父类or基类 ）
    IntelFactory（AbstractFactory的子类or派生类）：作用为进行了创建自定品牌的零件
    AmdFactory（AbstractFactory的子类or派生类）：作用为进行了创建自定品牌的零件
AbstractCpu（父类or基类 ）
    IntelCpu（AbstractCpu的子类or派生类）：作用为记录cup的型号
    AmdCpu（AbstractCpu的子类or派生类）：作用为记录cup的型号
AbstractMainboard（父类or基类 ）
    IntelMainBoard（AbstractMainboard的子类or派生类）：作用为记录主板的型号
    AmdMainBoard（AbstractMainboard的子类or派生类）：作用为记录主板的型号
ComputerEngineer（新式类）：作用为根据工厂对象（如IntelFactory()）让其组装自身型号的零件

抽象工厂和工厂模式的对比区别：
抽象工厂：规定死了，依赖限制，如上面实验，你用intel的机器只能配置intel的CPU不能配置AMD的CPU（由各自的工厂指定自己的产品生产品牌）
工厂模式：不是固定死的，举例：你可使用intel的机器配置AMD的CPU

优点
    1.分离接口和实现
　　客户端使用抽象工厂来创建需要的对象，而客户端根本就不知道具体的实现是谁，客户端只是面向产品的接口编程而已。
    也就是说，客户端从具体的产品实现中解耦。
    2.使切换产品族变得容易
　　因为一个具体的工厂实现代表的是一个产品族，比如上面例子的从Intel系列到AMD系列只需要切换一下具体工厂。
缺点
    1.不太容易扩展新的产品
　　如果需要给整个产品族添加一个新的产品，那么就需要修改抽象工厂，这样就会导致修改所有的工厂实现类。

'''


class AbstractFactory(metaclass=ABCMeta):
    computer_name = ''

    @abstractmethod
    def createCpu(self):
        pass

    @abstractmethod
    def createMainboard(self):
        pass


class IntelFactory(AbstractFactory):
    computer_name = 'Intel I7-series computer '

    def createCpu(self):
        return IntelCpu('I7-6500')

    def createMainboard(self):
        return IntelMainBoard('Intel-6000')


class AmdFactory(AbstractFactory):
    computer_name = 'Amd 4 computer '

    def createCpu(self):
        return AmdCpu('amd444')

    def createMainboard(self):
        return AmdMainBoard('AMD-4000')


class AbstractCpu(metaclass=ABCMeta):
    series_name = ''
    instructions = ''
    arch = ''


class IntelCpu(AbstractCpu):
    def __init__(self, series):
        self.series_name = series


class AmdCpu(AbstractCpu):
    def __init__(self, series):
        self.series_name = series


class AbstractMainboard(metaclass=ABCMeta):
    series_name = ''


class IntelMainBoard(AbstractMainboard):
    def __init__(self, series):
        self.series_name = series


class AmdMainBoard(AbstractMainboard):
    def __init__(self, series):
        self.series_name = series


class ComputerEngineer(object):

    def makeComputer(self, factory_obj):
        self.prepareHardwares(factory_obj)

    def prepareHardwares(self, factory_obj):
        self.cpu = factory_obj.createCpu()
        self.mainboard = factory_obj.createMainboard()

        info = '''------- computer [%s] info:
    cpu: %s
    mainboard: %s
 -------- End --------
        ''' % (factory_obj.computer_name, self.cpu.series_name, self.mainboard.series_name)
        print(info)


if __name__ == "__main__":
    engineer = ComputerEngineer()  # 装机工程师

    intel_factory = IntelFactory()  # intel工厂
    engineer.makeComputer(intel_factory)

    amd_factory = AmdFactory()  # adm工厂
    engineer.makeComputer(amd_factory)
