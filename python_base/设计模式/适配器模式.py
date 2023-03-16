from abc import ABCMeta, abstractmethod

'''
适配器模式

内容：将一个类的接口转换成客户希望的另一个接口。适配器模式使得原本由于接口
不兼容而不能一起工作的那些类可以一起工作

角色：
    目标接口（Target）
    待适配的类（Adaptee）
    适配器（Adaptor）

场景：
    想使用一个已经存在的类，但它的接口不符合要求

'''


# 目标接口（Target）
# 球员类
class Player(metaclass=ABCMeta):

    @abstractmethod
    def attack(self):
        pass

    @abstractmethod
    def defense(self):
        pass


# 前锋
class Forwards(Player):
    def __init__(self, name):
        self.name = name

    def attack(self):
        print("前锋%s 进攻" % self.name)

    def defense(self):
        print("前锋%s 防守" % self.name)


# 中锋（目标类）
class Center(Player):
    def __init__(self, name):
        self.name = name

    def attack(self):
        print("中锋%s 进攻" % self.name)

    def defense(self):
        print("中锋%s 防守" % self.name)


# 后卫
class Guards(Player):
    def __init__(self, name):
        self.name = name

    def attack(self):
        print("后卫%s 进攻" % self.name)

    def defense(self):
        print("后卫%s 防守" % self.name)


# 待适配的类（Adaptee）
# 外籍中锋（待适配类）
# 中锋
class ForeignCenter(object):

    def __init__(self, name):
        self.name = name

    def foreignAttack(self):
        print("外籍中锋%s 进攻" % self.name)

    def foreignDefense(self):
        print("外籍中锋%s 防守" % self.name)


# 适配器（Adaptor）
# 翻译（适配类）
class Translator(Player):

    def __init__(self, foreignPlayer):
        self.foreignPlayer = foreignPlayer

    def attack(self):
        self.foreignPlayer.foreignAttack()

    def defense(self):
        self.foreignPlayer.foreignDefense()


if __name__ == '__main__':
    b = Forwards('巴蒂尔')
    ym = Guards('姚明')
    m = Translator(ForeignCenter('麦克格雷迪'))
    b.attack()
    m.defense()
    m.attack()
    ym.defense()
