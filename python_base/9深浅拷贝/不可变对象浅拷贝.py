import copy

if __name__ == '__main__':
    # 简单不可变对象
    a = (1, 2, 3)
    b = copy.copy(a)
    print(id(a))
    print(id(b))

    # 不可变对象嵌套不可变对象
    a = (1, 2, 3, (4, 5))
    b = copy.copy(a)
    print(id(a))
    print(id(b))
    print(id(a[3]))
    print(id(b[3]))

    # 结论：对于不可变对象，浅拷贝不能拷贝外层，也不能拷贝内层对象。而是将引用指向同一个内存地址
