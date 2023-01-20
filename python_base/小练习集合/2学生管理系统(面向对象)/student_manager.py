from student import Student


class StudentManager(object):
    def __init__(self):
        self.students = []

    @staticmethod
    def show_menu():
        print('-' * 40)
        print('1.增加学生信息')
        print('2.删除学生信息')
        print('3.修改学生信息')
        print('4.查看学生信息')
        print('5.查看全部学生信息')
        print('6.保存系统录入信息')
        print('7.退出系统')
        print('-' * 40)

    def add_user(self):
        name = input("name: ")
        age = int(input("age: "))
        address = input("address: ")
        self.students.append(Student(name, age, address))
        print("add success")

    def del_user(self):
        del_name = input("del name: ")
        for student in self.students:
            if student.name == del_name:
                print("del success")
                self.students.remove(student)
                break
        else:
            print("del fail")

    def update_user(self):
        update_name = input("update name: ")
        for student in self.students:
            if student.name == update_name:
                student.name = input("name: ")
                student.age = int(input("age: "))
                student.address = input("address: ")
                print("update success")
                break
        else:
            print("update fail")

    def find_user(self):
        find_name = input("find name: ")
        for student in self.students:
            if student.name == find_name:
                print(student)
                break
        else:
            print("find fail")

    def find_all_user(self):
        for student in self.students:
            print(student)

    def save_data(self):
        try:
            # 注意编码格式
            f = open('save_student.txt', 'w', encoding='utf-8')
            # 将列表中的对象类型转换为字典类型，存储至文件中
            students = [student.__dict__ for student in self.students]
            f.write(str(students))
        except Exception as e:
            print(f'exception: {e}')
        else:
            print('save success')
        finally:
            f.close()

    # 私有方法
    def __load_data(self):
        try:
            f = open('save_student.txt', 'r', encoding='utf-8')
            # 转换字符串类型的列表为python的列表
            students = eval(f.read())
            # 将文件中保存的字典类型转换为对象类型，写入成员变量的列表中
            self.students = [Student(student['name'], student['age'], student['address']) for student in students]
        except Exception as e:
            print(f'exception: {e}')
        else:
            print('load success')
        finally:
            f.close()

    def start(self):
        self.__load_data()
        while True:
            self.show_menu()
            user_choice = int(input('user choice: '))
            if user_choice == 1:
                self.add_user()
            elif user_choice == 2:
                self.del_user()
            elif user_choice == 3:
                self.update_user()
            elif user_choice == 4:
                self.find_user()
            elif user_choice == 5:
                self.find_all_user()
            elif user_choice == 6:
                self.save_data()
            elif user_choice == 7:
                print('欢迎再次使用本系统')
                break
            else:
                print('输入有误，请重新输入')
