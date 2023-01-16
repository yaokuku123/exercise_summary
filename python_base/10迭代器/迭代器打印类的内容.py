from collections.abc import Iterator, Iterable


class StudentManager(object):
    def __init__(self):
        self.students = []
        self.index = 0

    def add(self, name, age, address):
        student_dict = {}
        student_dict['name'] = name
        student_dict['age'] = age
        student_dict['address'] = address
        self.students.append(student_dict)

    # 将本类作为可迭代对象返回
    def __iter__(self):
        return self

    # 将本类作为迭代器返回
    def __next__(self):
        if self.index < len(self.students):
            item = self.students[self.index]
            self.index += 1
            return item
        else:
            raise StopIteration


if __name__ == '__main__':
    student_manager = StudentManager()
    student_manager.add('yorick', 25, 'bj')
    student_manager.add('yorick2', 26, 'tj')
    student_manager.add('yorick3', 27, 'nj')
    print(isinstance(student_manager, Iterable))  # __iter__重写
    print(isinstance(student_manager, Iterator))  # __iter__ __next__ 重写
    # for本质是通过__iter__判断当前对象是否是可迭代的，若可迭代则获取循环类的迭代器对象，
    # 通过循环调用迭代器的__next__方法，完成数据输出。当迭代完毕后抛出StopIteration异常，表示循环结束
    for student in student_manager:
        print(student)

    # list set dict 都是可迭代的，但需要通过iter()函数获取其迭代器对象
    my_list = [1, 2, 3]
    print(isinstance(my_list, Iterable))  # __iter__重写
    print(isinstance(my_list, Iterator))  # __next__ 未重写
    my_list_iter = iter(my_list)
    print(isinstance(my_list_iter, Iterable))  # __iter__重写
    print(isinstance(my_list_iter, Iterator))  # __next__ 重写
