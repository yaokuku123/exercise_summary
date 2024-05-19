from abc import ABCMeta, abstractmethod

'''
抽象工厂模式

内容：定义一个工厂类接口，让工厂子类来创建一系列相关或相互依赖的对象。
相比工厂方法模式生产一种产品，抽象工厂模式中的每个具体工厂都生产一套产品。
例：生产一部手机，需要CPU和OS对象进行组装，其中每类对象都有不同的种类。
对每个具体工厂，分别生产一部手机需要的CPU和OS对象。

角色：
    抽象工厂角色（Creator）
    具体工厂角色（Concrete Creator）
    抽象产品角色（Product）
    具体产品角色（Concrete Product）
    客户端（Client）
    
优点：
    1 将客户端与类的具体实现相分离
    2 每个工厂创建了一个完整的产品系列，使得易于交换产品系列
    3 有利于产品的一致性（即产品之间的约束关系）
缺点：
    1 难以支持新种类的（抽象）产品

'''


# 抽象产品角色（Product）
class Cpu(metaclass=ABCMeta):
    @abstractmethod
    def show_cpu(self):
        pass


# 抽象产品角色（Product）
class Os(metaclass=ABCMeta):
    @abstractmethod
    def show_os(self):
        pass


# 具体产品角色（Concrete Product）
class HuaWeiCpu(Cpu):
    def show_cpu(self):
        print('华为CPU')


# 具体产品角色（Concrete Product）
class AppleCpu(Cpu):
    def show_cpu(self):
        print('苹果CPU')


# 具体产品角色（Concrete Product）
class AndroidOs(Os):
    def show_os(self):
        print('安卓系统')


# 具体产品角色（Concrete Product）
class AppleOs(Os):
    def show_os(self):
        print('苹果系统')


# 抽象工厂角色（Creator）
class PhoneFactory(metaclass=ABCMeta):
    @abstractmethod
    def make_cpu(self):
        pass

    @abstractmethod
    def make_os(self):
        pass


# 具体工厂角色（Concrete Creator）
class HuaWeiPhoneFactory(PhoneFactory):
    def make_cpu(self):
        return HuaWeiCpu()

    def make_os(self):
        return AndroidOs()


# 具体工厂角色（Concrete Creator）
class ApplePhoneFactory(PhoneFactory):
    def make_cpu(self):
        return AppleCpu()

    def make_os(self):
        return AppleOs()


# 最终组装物件
class Phone(object):
    def __init__(self, cpu, os):
        self.cpu = cpu
        self.os = os

    def show_info(self):
        self.cpu.show_cpu()
        self.os.show_os()


# 客户端（Client）
def make_phone(factory):
    cpu = factory.make_cpu()
    os = factory.make_os()
    return Phone(cpu, os)


if __name__ == '__main__':
    huawei_phone = make_phone(HuaWeiPhoneFactory())
    huawei_phone.show_info()
    print('-' * 20)
    apple_phone = make_phone(ApplePhoneFactory())
    apple_phone.show_info()
