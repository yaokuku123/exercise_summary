class Student(object):
    """
        Student 文档内容
    """
    # 动态语言限制，只允许添加下面三个属性，其他的不允许添加
    # 注意：1.父类的__slots__不会被子类继承
    # 2.只能限制成员属性，不能限制类属性
    # 3.默认就不能再访问__dict__属性，但可以将其加入至__slots__中，但会导致__slots__失效，无法加入到__slots__中的属性会加入到__dict__中
    __slots__ = ('name', 'age', 'study_classes', '__dict__')

    def __init__(self, name, age):
        self.name = name
        self.age = age
        self.study_classes = {}

    def add_study_class(self, classname, score):
        self.study_classes[classname] = score

    # 属性访问拦截器，当调用对象的属性时会被该方法拦截，这个方法的返回值就是最终得到的内容
    def __getattribute__(self, item):
        # print(f'getattribute item: {item}')
        return super().__getattribute__(item)  # 调用父类object的该方法，默认

    # 对象直接加()，像函数那样调用对象时，调用该__call__方法
    def __call__(self, *args, **kwargs):
        print('call...')

    # print()函数默认打印内容
    def __str__(self):
        return f'TestDemo: name: {self.name}, age: {self.age}'

    # 终端直接回车呈现的内容
    def __repr__(self):
        return f'TestDemo: name: {self.name}, age: {self.age}'

    # 索引，获取内容
    def __getitem__(self, item):
        return self.study_classes[item]

    # 索引，设置内容
    def __setitem__(self, key, value):
        self.study_classes[key] = value

    # 索引，删除内容
    def __delitem__(self, key):
        del self.study_classes[key]


if __name__ == '__main__':
    student = Student('yorick', 25)
    print(student.name)

    # 将实例对象的属性信息以字典的形式返回
    # print(student.__dict__)
    # 将类的属性信息以字典的形式返回
    print(Student.__dict__)

    # 获取文档注释
    print(student.__doc__)

    # 获取当前对象的模块
    print(student.__module__)

    # 获取当前对象的类对象
    print(student.__class__)
    print(type(student.__class__))

    # 调用__call__方法
    student()

    # 添加一些课程
    student.add_study_class('c++', 98)
    student.add_study_class('java', 99)
    student.add_study_class('python', 95)
    print(student.study_classes)
    # 索引，获取内容
    print(student['c++'])
    # 索引，设置内容
    student['solidity'] = 100
    print(student.study_classes)
    # 索引，删除内容
    del student['c++']
    print(student.study_classes)
