def test1():
    s = 'hello'
    # 不同的对齐方式，默认是空格填充
    print(s.ljust(10, '*'))
    print(s.rjust(10, '^'))
    print(s.center(10, '-'))


def test2():
    num = 100
    # 左对齐10
    print(format(num, '<10'))
    # 右对齐10
    print(format(num, '>10'))
    # 居中对齐10
    print(format(num, '^10'))
    # 居中对齐10，*填充
    print(format(num, '*^10'))
    # 右对齐10，带符号（正负号）
    print(format(num, '>+10'))
    # 右对齐10，符号位置不动
    print(format(num, '=+10'))
    # 右对齐10，符号位置不动，0填充
    print(format(num, '0=+10'))


# 方法1：使用字符串的 ljust,rjust,center 方法进行左，右，居中对齐
def method1(d: dict):
    # 找到最长的键
    w = max(map(len, d.keys()))
    print(w)
    for k, v in d.items():
        print(k.ljust(w), ':', v)


# 方法2：使用format函数
def method2(d: dict):
    # 找到最长的键
    w = max(map(len, d.keys()))
    print(w)
    for k, v in d.items():
        print(format(k, f'<{w}'), ':', v)


if __name__ == '__main__':
    my_dict = {'lodDis': 100, 'SmallCull': 0.04, 'DistCull': 500, 'trilinear': 40, 'farclip': 477}
    method1(my_dict)
    method2(my_dict)
