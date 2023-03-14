from functools import total_ordering


# 添加total_ordering注解，可以使得被装饰的类只实现__eq__和额外随便一个比较函数即可，
# 其他函数可自动通过这两个函数推断，减少编写代码量
@total_ordering
class Student(object):
    def __init__(self, name, age):
        self.name = name
        self.age = age

    def __lt__(self, other):
        return self.age < other.age

    def __eq__(self, other):
        return self.age == other.age


if __name__ == '__main__':
    # 自定义比较规则，使得Student对象可以通过依据年龄来比较大小
    stu1 = Student('yorick', 25)
    stu2 = Student('tom', 30)
    print(stu1 > stu2)  # False
