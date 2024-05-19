from random import randint


# 根据字典中的值大小进行从大到小的排序，并返回包含排名的字典

# 方法一: 通过将字典转化为（值，键）元组列表，再通过sorted函数通过比较元组大小完成排序
# 元组可以完成比较，先比较第一个数据大小，再以此类推
def method1(student_score_dict):
    # 1.将字典转换为（值，键）元组列表的形式
    # 方式1：使用列表解析转换为（值，键）元组列表
    list1 = [(v, k) for k, v in student_score_dict.items()]
    print(list1)
    # 方式2：使用zip函数转换为（值，键）元组列表
    # zip函数会将两个列表的数据依次俩俩合并为元组
    list2 = list(zip(student_score_dict.values(), student_score_dict.keys()))
    print(list2)

    # 2.sorted排序
    sorted_list = sorted(list1, reverse=True)
    print(sorted_list)

    # 3.转换为包含排名的字典
    # enumerate()可以添加序号，1表示起始数值，默认从0开始
    rank_student_score_dict = {v: (i, k) for i, (k, v) in enumerate(sorted_list, 1)}
    return rank_student_score_dict


# 方法二：直接通过sorted函数，添加key参数完成排序
def method2(student_score_dict):
    # 1.直接通过sorted函数添加key参数完成排序
    # key表示要作为比较的数据
    sorted_list = sorted(student_score_dict.items(), key=lambda item: item[1], reverse=True)
    # 2.转换为包含排名的字典
    rank_student_score_dict = {v: (i, k) for i, (k, v) in enumerate(sorted_list, 1)}
    return rank_student_score_dict


if __name__ == '__main__':
    student_score_dict = {k: randint(60, 100) for k in 'abcdefg'}
    print(student_score_dict)
    print(method1(student_score_dict))
    print(method2(student_score_dict))

