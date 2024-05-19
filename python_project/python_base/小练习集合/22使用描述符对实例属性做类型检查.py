class Attr(object):
    def __init__(self, key, type_):
        self.key = key
        self.type_ = type_

    # 给对象属性赋值时调用
    def __set__(self, instance, value):
        print('__set__')
        # 完成对属性类型的校验
        if not isinstance(value, self.type_):
            raise TypeError('type error!')
        # 给对象属性赋值
        instance.__dict__[self.key] = value

    # 获取对象属性时调用
    def __get__(self, instance, cls):
        print('__get__')
        return instance.__dict__[self.key]

    # 删除对象属性时调用
    def __delete__(self, instance):
        print('__delete__')
        del instance.__dict__[self.key]


class Student(object):
    # 在类属性中定义描述符，对对象的该属性赋值时会调用描述符中规定的逻辑对类型进行判别
    name = Attr('name', str)
    age = Attr('age', int)

    def __init__(self, name, age):
        self.name = name
        self.age = age


if __name__ == '__main__':
    # 只有按照指定的类型传递时，才可以通过描述符的检测
    stu1 = Student('yorick', 25)  # ok
    print(stu1.name)
    del stu1.name
    # stu2 = Student('yorick', '25')  # TypeError
