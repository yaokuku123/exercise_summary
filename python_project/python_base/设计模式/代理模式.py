from abc import ABCMeta, abstractmethod

'''
代理模式

内容：为其他对象提供一种代理以控制对这个对象的访问

角色：
    抽象实体（Subject）
    实体（RealSubject）
    代理（Proxy）

场景：
    1 远程代理：为远程的对象访问提供代理
    2 虚代理：根据需要创建大对象
    3 保护代理：控制对原始对象的访问，用于对象有不同访问权限时

'''


# 抽象实体（Subject）
class Subject(metaclass=ABCMeta):
    @abstractmethod
    def getContent(self):
        pass

    @abstractmethod
    def setContent(self, content):
        pass


# 实体（RealSubject）
class RealSubject(Subject):
    def __init__(self, filename):
        self.filename = filename
        with open(filename, 'r', encoding='utf-8') as f:
            self.content = f.read()

    def getContent(self):
        return self.content

    def setContent(self, content):
        with open(self.filename, 'w', encoding='utf-8') as f:
            f.write(content)


# 代理（Proxy）
# 虚代理
class VirtualProxy(Subject):
    def __init__(self, filename):
        self.filename = filename
        self.realSubject = None

    def getContent(self):
        if not self.realSubject:
            self.realSubject = RealSubject(self.filename)
        return self.realSubject.getContent()

    def setContent(self, content):
        if not self.realSubject:
            self.realSubject = RealSubject(self.filename)
        self.realSubject.setContent(content)


# 代理（Proxy）
# 保护代理
class ProtectProxy(Subject):
    def __init__(self, filename):
        self.realSubject = RealSubject(filename)

    def getContent(self):
        return self.realSubject.getContent()

    def setContent(self, content):
        raise PermissionError('access control error!')


if __name__ == '__main__':
    sub = VirtualProxy('data.txt')
    print(sub.getContent())
    sub2 = ProtectProxy('data.txt')
    sub2.setContent('protect data')
