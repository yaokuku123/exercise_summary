from abc import ABCMeta, abstractmethod

'''
Adapter

适配器模式
将一个类的接口转换成客户希望的另外一个接口。使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
应用场景：希望复用一些现存的类，但是接口又与复用环境要求不一致。

Player：(父类or基类）
国内
Forwards（Player的子类or派生类）：作用为国内球员的动作方法
Center（Player的子类or派生类）：作用为国内球员的动作方法
Guards（Player的子类or派生类）：作用为国内球员的动作方法
国外：
ForeignCenter：作用为国外球员的动作方法（动作虽然一样但是动作方法的名字和国内动作方法的名字不一样）
Translator（Player的子类or派生类）：作用为适配器，国内球员的动作方法的名字一样（但是方法内调用了国外球员对象的动作方法）

'''


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


# 外籍中锋（待适配类）
# 中锋
class ForeignCenter(object):

    def __init__(self, name):
        self.name = name

    def foreignAttack(self):
        print("外籍中锋%s 进攻" % self.name)

    def foreignDefense(self):
        print("外籍中锋%s 防守" % self.name)


# 翻译（适配类）
class Translator(Player):

    def __init__(self, foreignPlayer):
        self.foreignPlayer = foreignPlayer

    def attack(self):
        self.foreignPlayer.foreignAttack()

    def defense(self):
        self.foreignPlayer.foreignDefense()


def clientUI():
    b = Forwards('巴蒂尔')
    ym = Guards('姚明')
    m = Translator(ForeignCenter('麦克格雷迪'))

    b.attack()
    m.defense()
    ym.attack()
    b.defense()
    return


if __name__ == '__main__':
    clientUI()
