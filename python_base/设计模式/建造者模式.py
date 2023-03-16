from abc import ABCMeta, abstractmethod

'''
建造者模式

内容：将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示
建造者模式与抽象工厂模式相似，也用来创建复杂对象。主要区别时建造者模式着重一步步
构建一个复杂对象，而抽象工厂模式着重于创建多个系列的产品对象

角色：
    抽象建造者（Builder）
    具体建造者（Concrete Builder）
    产品（Product）
    指挥者（Director）
    
优点：
    1 隐藏了一个产品的内部构造与装配过程
    2 将构造代码与表示代码分开
    3 可以对构造过程进行更精细的控制

'''


# 产品（Product）
class Player(object):
    def __init__(self, head=None, body=None, arm=None, leg=None):
        self.head = head
        self.body = body
        self.arm = arm
        self.leg = leg

    def __str__(self):
        return f'player => head:{self.head},body:{self.body},arm:{self.arm},leg:{self.leg}'


# 抽象建造者（Builder）
class PlayerBuilder(metaclass=ABCMeta):
    @abstractmethod
    def buildHead(self):
        pass

    @abstractmethod
    def buildBody(self):
        pass

    @abstractmethod
    def buildArm(self):
        pass

    @abstractmethod
    def buildLeg(self):
        pass


# 具体建造者（Concrete Builder）
class PlayerFatBuilder(PlayerBuilder):
    def __init__(self):
        self.player = Player()

    def buildHead(self):
        self.player.head = '大头'

    def buildBody(self):
        self.player.body = '肥胖'

    def buildArm(self):
        self.player.arm = '粗胳膊'

    def buildLeg(self):
        self.player.leg = '大象腿'


# 具体建造者（Concrete Builder）
class PlayerThinBuilder(PlayerBuilder):
    def __init__(self):
        self.player = Player()

    def buildHead(self):
        self.player.head = '小头'

    def buildBody(self):
        self.player.body = '瘦弱'

    def buildArm(self):
        self.player.arm = '细胳膊'

    def buildLeg(self):
        self.player.leg = '小细腿'


# 指挥者（Director）
class PlayerDirector():

    def __init__(self, builder):
        self.builder = builder

    def createPlayer(self):
        self.builder.buildHead()
        self.builder.buildBody()
        self.builder.buildArm()
        self.builder.buildLeg()
        return self.builder.player


if __name__ == '__main__':
    fat_player = PlayerDirector(PlayerFatBuilder()).createPlayer()
    print(fat_player)
    thin_player = PlayerDirector(PlayerThinBuilder()).createPlayer()
    print(thin_player)
