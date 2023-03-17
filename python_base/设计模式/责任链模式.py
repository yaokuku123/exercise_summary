from abc import ABCMeta, abstractmethod

'''
责任链模式

内容：使多个对象都有机会处理请求，从而避免请求的发送者和接收者之间的耦合关系。
将这些对象连成一条链，并沿着这条链传递该请求，直到有一个对象处理它为止

角色：
    抽象处理者（Handler）
    具体处理者（Concrete Handler）
    客户端（Client）

场景：
    1 有多个对象可以处理一个请求，具体哪个对象处理由运行时决定
    2 在不明确接收者的情况下，向多个对象中的一个提交一个请求

优点：
    降低耦合度，一个对象无需知道是其中哪个对象处理其请求

'''

# 抽象处理者（Handler）
class Handler(metaclass=ABCMeta):
    @abstractmethod
    def handleLeave(self, day):
        pass

# 具体处理者（Concrete Handler）
class GeneralManager(Handler):
    def handleLeave(self, day):
        if day <= 10:
            print(f'总经理准假：{day}')
        else:
            print('辞职吧')

# 具体处理者（Concrete Handler）
class DepartmentManager(Handler):
    def __init__(self):
        self.next = GeneralManager()

    def handleLeave(self, day):
        if day <= 5:
            print(f'部门经理准假：{day}')
        else:
            print('部门经理无审批权限')
            self.next.handleLeave(day)

# 具体处理者（Concrete Handler）
class ProjectManager(Handler):
    def __init__(self):
        self.next = DepartmentManager()

    def handleLeave(self, day):
        if day <= 2:
            print(f'项目经理准假：{day}')
        else:
            print('项目经理无审批权限')
            self.next.handleLeave(day)


if __name__ == '__main__':
    day = 11
    ProjectManager().handleLeave(day)
