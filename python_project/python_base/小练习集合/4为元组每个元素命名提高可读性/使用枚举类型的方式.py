from enum import IntEnum


# 定义Student的枚举类
class StudentEnum(IntEnum):
    # 通过类属性的方式，定义枚举
    NAME = 0
    AGE = 1
    SEX = 2
    EMAIL = 3


if __name__ == '__main__':
    s = ('yorick', 23, 'male', 'yorick@163.com')
    # 由于是int类型的子类，所以可以类似下标的方式访问元组数据
    print(isinstance(StudentEnum.NAME, int))  # True
    print(StudentEnum.NAME == 0)  # True
    # 通过枚举类的方式访问元组
    print(s[StudentEnum.NAME], s[StudentEnum.AGE], s[StudentEnum.SEX], s[StudentEnum.EMAIL])
