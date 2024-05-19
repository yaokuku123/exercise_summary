import time
# 1.引入线程模块 threading
import threading


# 子线程-任务1，传参：n：循环次数，t：停顿时间
def music(n, t):
    for i in range(n):
        # 获取当前线程信息
        cur_thread = threading.current_thread()
        print(f'thread: {cur_thread.name} music...')
        time.sleep(t)


# 子线程-任务2
def speak(n, t):
    for i in range(n):
        cur_thread = threading.current_thread()
        print(f'thread: {cur_thread.name} speak...')
        time.sleep(t)


if __name__ == '__main__':
    # 2.创建线程对象
    # target：子线程运行的函数
    # args：指定传入的参数，以元组的形式，按顺序传参
    # kargs：指定传入的参数，以字典的形式，与函数形参名称对应，args与kargs传参功能一致
    thread_music = threading.Thread(target=music, args=(5, 0.2))
    thread_speak = threading.Thread(target=speak, kwargs={'n': 5, 't': 0.2})
    # 3.启动线程
    thread_music.start()
    thread_speak.start()
