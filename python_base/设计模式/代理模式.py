from abc import ABCMeta, abstractmethod

'''
Proxy

代理模式
应用特性：需要在通信双方中间需要一些特殊的中间操作时引用，多加一个中间控制层。
结构特性：建立一个中间类，创建一个对象，接收一个对象，然后把两者联通起来
'''


# 接口
class ITeacherDao(metaclass=ABCMeta):
    @abstractmethod
    def teach(self):
        pass


# 被代理对象
class TeacherDao(ITeacherDao):
    def teach(self):
        print('老师上课')


# 代理对象
class TeacherDaoProxy(ITeacherDao):
    def __init__(self, teacherDao):
        self.teacherDao = teacherDao

    def teach(self):
        print('准备教材')
        self.teacherDao.teach()
        print('下课')


if '__main__' == __name__:
    # 创建代理对象，同时将被代理对象传递给代理对象
    teach_proxy = TeacherDaoProxy(TeacherDao())
    # 通过代理对象，调用被代理对象的增强方法
    teach_proxy.teach()
