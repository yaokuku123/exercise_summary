if __name__ == '__main__':
    # 可变对象的赋值，仅是引用的赋值，让b的引用指向a
    a = [1, 2, 3]
    b = a
    print(id(a))  # 140423687959728
    print(id(b))  # 140423687959728

    # 不可变对象的赋值，仅是引用的赋值，让b的引用指向a
    a = (1, 2, 3)
    b = a
    print(id(a))  # 140423689373088
    print(id(b))  # 140423689373088

    # 结论：赋值操作，对于可变和不可变对象都是直接引用地址的赋值，指向相同对象
