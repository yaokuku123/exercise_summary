from abc import ABCMeta, abstractmethod

'''
观察者模式

内容：定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的
对象都会得到通知并被自动更新，观察者模式又称"发布-订阅"模式

角色：
    抽象主题（Subject）
    具体主题（Concrete Subject）-- 发布者
    抽象观察者（Observer）
    具体观察者（Concrete Observer）-- 订阅者
    
场景：
    1 当一个抽象模型有两方面，其中一个方面依赖于另一个方面，将这两者封装在独立对象
    中以使它们可以各自独立地改变和复用
    2 当对一个对象的改变需要同时改变其他对象，而不知道具体有多少对象待改变
    3 当一个对象必须通知其他对象，而它又不能假定其他对象是谁。即不希望这些对象紧耦合

优点：
    1 目标和观察者之间的抽象耦合最小
    2 支持广播通信

'''


# 抽象观察者（Observer）
class Observer(metaclass=ABCMeta):
    @abstractmethod
    def update(self, notice):
        pass


# 抽象主题（Subject）
class Notice(object):
    def __init__(self):
        self.observers = []

    def attach(self, obs):
        self.observers.append(obs)

    def detach(self, obs):
        self.observers.remove(obs)

    def notify(self):
        for observer in self.observers:
            observer.update(self)


# 具体主题（Concrete Subject）
class StaffNotice(Notice):
    def __init__(self, msg):
        super().__init__()
        self.__msg = msg

    @property
    def msgInfo(self):
        return self.__msg

    @msgInfo.setter
    def msgInfo(self, msg):
        self.__msg = msg
        self.notify()


# 具体观察者（Concrete Observer）
class Staff(Observer):
    def __init__(self):
        self.msg = None

    def update(self, notice):
        self.msg = notice.msgInfo


if __name__ == '__main__':
    staff1 = Staff()
    staff2 = Staff()
    staff_notice = StaffNotice('init')
    staff_notice.attach(staff1)
    staff_notice.attach(staff2)
    staff_notice.msgInfo = 'hello world'
    print(staff1.msg)
    print(staff2.msg)
