from random import randint

# 题目：在列表、集合、字典中根据条件筛选过滤数据
if __name__ == '__main__':
    # 1.列表，筛选不为负数的数据集合
    # 创建列表
    # 创建包含10个元素的列表，值范围[-10,10]，_表示不关心遍历的数值
    my_list = [randint(-10, 10) for _ in range(10)]
    print(my_list)
    # 1.1 使用列表解析
    list_res1 = [x for x in my_list if x >= 0]
    print(list_res1)
    # 1.2 使用filter函数
    list_generator = filter(lambda x: x >= 0, my_list)
    # list()函数将得到的生成器展开
    list_res2 = list(list_generator)
    print(list_res2)
    print('-' * 40)

    # 2.集合，筛选可以被3整除的数据集合
    # 创建集合
    # 创建包含10个元素的集合，值范围[0,20]
    my_set = {randint(0, 20) for _ in range(10)}
    print(my_set)
    # 2.1 使用集合解析
    set_res1 = {x for x in my_set if x % 3 == 0}
    print(set_res1)
    # 2.2 使用filter函数
    set_generator = filter(lambda x: x % 3 == 0, my_set)
    set_res2 = set(set_generator)
    print(set_res2)

    # 3.字典，筛选成绩不低于90的学生
    # 创建字典
    # 创建20个学号从1到20的学生字典，成绩范围[50,100]
    my_dict = {'student%d' % i: randint(50, 100) for i in range(1, 21)}
    print(my_dict)
    # 3.1 使用字典解析
    dict_res1 = {k: v for k, v in my_dict.items() if v >= 90}
    print(dict_res1)
    # 3.2 使用filter函数
    # item为取到的键值对元组
    dict_generator = filter(lambda item: item[1] >= 90, my_dict.items())
    dict_res2 = dict(dict_generator)
    print(dict_res2)
