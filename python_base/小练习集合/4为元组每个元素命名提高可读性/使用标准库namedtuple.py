from collections import namedtuple

if __name__ == '__main__':
    # 定义命名元组类，'Student'传入该元组的名字，[]传入每个元组数据的名字
    Student = namedtuple('Student', ['name', 'age', 'sex', 'email'])
    # 创建命名元组对象
    s = Student('yorick', 23, 'male', 'yorick@163.com')
    # 是元组的子类，可以向常规元组那样使用
    print(isinstance(s, tuple))  # True
    print(s[0], s[1], s[2], s[3])
    # 向对象的方式访问元组每个数据
    print(s.name, s.age, s.sex, s.email)
