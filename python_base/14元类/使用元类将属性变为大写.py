def upper_attr(class_name, class_parents, class_attr):
    # 定义新的类属性字典
    new_attr = {}
    for key, val in class_attr.items():
        # 如果不是以__开头，则变为大写后放到新的属性字典中
        if not key.startswith('__'):
            new_attr[key.upper()] = val
    # 使用type创建类
    return type(class_name, class_parents, new_attr)


# metaclass指定该类处理的元类函数
class TestDemo(object, metaclass=upper_attr):
    num = 100


if __name__ == '__main__':
    # 类属性num变为大写NUM
    print(hasattr(TestDemo, 'num'))  # False
    print(hasattr(TestDemo, 'NUM'))  # True
