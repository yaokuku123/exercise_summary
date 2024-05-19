from collections import deque
import pickle

# 通过deque记录用户输入的容量为5的历史记录，并在退出时保存磁盘，再次登陆时从磁盘加载的功能
if __name__ == '__main__':
    try:
        # 通过pickle模块从磁盘读取deque对象
        dq = pickle.load(open('save.pkl', 'rb'))
    except Exception as e:
        # 没有读取到则创建新的deque对象
        dq = deque([], 5)

    while True:
        user_info = input('enter your msg: ')
        if user_info == 'quit':
            # 通过pickle保存用户的历史数据
            pickle.dump(dq, open('save.pkl', 'wb'))
            break
        elif user_info == 'history':
            print(list(dq))
        else:
            print(f'your input is {user_info}')
            # 向deque中存储历史数据
            dq.append(user_info)
