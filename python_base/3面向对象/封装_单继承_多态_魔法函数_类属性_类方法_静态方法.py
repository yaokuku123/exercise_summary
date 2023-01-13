# 定义父类
class Person(object):
    # 构造方法
    def __init__(self, name, age, gender):
        # 属性或方法前加 __ 表示私有，仅类内可以访问
        self.__name = name
        self.__age = age
        self.__gender = gender

    # print打印内容
    def __str__(self):
        return f'person => name: {self.__name}, age: {self.__age}, gender: {self.__gender}'

    # 析构函数
    def __del__(self):
        print('person del...')

    # 父类方法1
    def eat(self):
        print('person eating...')

    # 父类方法2
    def call(self):
        print('person call...')

    # 提供获取私有属性的方法
    def get_name(self):
        return self.__name

    def get_age(self):
        return self.__age

    def get_gender(self):
        return self.__gender


# 定义子类继承父类
class Student(Person):
    # 类属性
    count = 0

    # 静态方法，不需要使用成员或者类的属性或方法
    @staticmethod
    def hello_world():
        print('hello world student...')

    # 类方法，需要使用类的属性或方法
    @classmethod
    def get_student_count(cls):
        return cls.count

    # 构造方法
    def __init__(self, name, age, gender, score):
        # 调用父类的构造方法
        super().__init__(name, age, gender)
        self.__score = score
        # 类属性赋值
        Student.count += 1

    # 重写父类的该方法
    def __str__(self):
        return f'student => name: {self.get_name()}, age: {self.get_age()}, ' \
               f'gender: {self.get_gender()}, score: {self.__score}'

    # 重写父类的该方法
    def __del__(self):
        print('student del...')

    # 重写父类的该方法
    def eat(self):
        print('student eating...')


# 多态演示
def service(obj: Person):
    obj.eat()


if __name__ == '__main__':
    student1 = Student('yorick', 25, 'male', 90)
    student2 = Student('tom', 24, 'male', 88)
    print(student1)
    # 多态
    service(student1)
    # 获取类属性值
    print(Student.count)
    # 调用类方法
    print(Student.get_student_count())
    # 调用静态方法
    Student.hello_world()
