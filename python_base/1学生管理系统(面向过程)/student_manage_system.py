import os

students = []


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


def add_user():
    student = {}
    name = input('name: ')
    age = int(input('age: '))
    address = input('address: ')
    student['name'] = name
    student['age'] = age
    student['address'] = address
    students.append(student)
    print('add success')


def del_user():
    del_name = input('del name: ')
    for student in students:
        if student['name'] == del_name:
            print('del success')
            students.remove(student)
            break
    else:
        print('del fail, not found the student')


def update_user():
    update_name = input('update name: ')
    for student in students:
        if student['name'] == update_name:
            print('update success')
            student['name'] = input('new name: ')
            student['age'] = int(input('new age: '))
            student['address'] = input('new address: ')
            break
    else:
        print('update fail, not found the student')


def find_user():
    find_name = input('name: ')
    for student in students:
        if student['name'] == find_name:
            print(student)
            break
    else:
        print('find fail, not found the student')


def find_all_user():
    for student in students:
        print(student)


def save_data():
    try:
        f = open('./save_student.txt', 'w', encoding='utf-8')
        global students
        f.write(str(students))
    except Exception as e:
        print(f'file open error: {e}')
    else:
        print('save success')
    finally:
        f.close()


def load_data():
    try:
        f = open('./save_student.txt', 'r', encoding='utf-8')
        global students
        students = eval(f.read())
    except Exception as e:
        print(f'load read error: {e}')
    else:
        print('load success')
    finally:
        f.close()


def run():
    load_data()
    while True:
        show_menu()
        user_choice = int(input('请输入操作选项：'))
        if (user_choice == 1):
            add_user()
        elif (user_choice == 2):
            del_user()
        elif (user_choice == 3):
            update_user()
        elif (user_choice == 4):
            find_user()
        elif (user_choice == 5):
            find_all_user()
        elif (user_choice == 6):
            save_data()
        elif (user_choice == 7):
            print('欢迎再次使用本系统')
            break
        else:
            print('输入有误，请重新输入')


if __name__ == '__main__':
    run()
