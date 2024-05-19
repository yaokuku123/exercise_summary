if __name__ == '__main__':
    # 生成器可以节省能耗，不是直接将所有内容全部生成出来放置内存中，而是每次调用时生成1个数据
    # 生成器推导式写法，需要 () 包裹，里面写推导式
    my_generator = (i * 2 for i in range(10))
    # 调用 next() 函数时，会实时生成指定数据
    print(next(my_generator))
    print(next(my_generator))
    # 使用 for 循环遍历生成器，获取剩余数据，直到结束
    for val in my_generator:
        print(val)
