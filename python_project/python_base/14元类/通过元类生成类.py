class Father(object):
    num = 100


def member_func(self):
    print(f'member_func, num:{self.num}...')


@classmethod
def class_func(cls):
    print(f'class_func, num:{cls.num}...')


@staticmethod
def static_func():
    print('static_func...')


if __name__ == '__main__':
    # 'Student' 使用元类创建的类名
    # (Father,) 该类继承的父类
    # {} 传入类的属性和方法
    Student = type('Student', (Father,),
                   {'member_func': member_func, 'class_func': class_func, 'static_func': static_func})
    student = Student()
    student.member_func()
    Student.class_func()
    Student.static_func()
