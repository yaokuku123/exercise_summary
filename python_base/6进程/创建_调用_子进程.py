import os
import time
# 1.引入进程相关的模块 multiprocessing
import multiprocessing


# 子进程-任务1，传参：n：循环次数，t：停顿时间
def music(n, t):
    for i in range(n):
        # 获取当前进程pid
        pid = os.getpid()
        # 获取当前进程的父进程pid
        father_pid = os.getppid()
        print(f'subprocess pid:{pid}, father_pid: {father_pid} music...')
        time.sleep(t)


# 子进程-任务2
def speak(n, t):
    for i in range(n):
        pid = os.getpid()
        father_pid = os.getppid()
        print(f'subprocess pid:{pid}, father_pid: {father_pid} speak...')
        time.sleep(t)


if __name__ == '__main__':
    # 2.创建进程对象
    # target：子进程运行的函数
    # args：指定传入的参数，以元组的形式，按顺序传参
    # kargs：指定传入的参数，以字典的形式，与函数形参名称对应，args与kargs传参功能一致
    sub_process_music = multiprocessing.Process(target=music, args=(5, 0.2))
    sub_process_speak = multiprocessing.Process(target=speak, kwargs={'n': 5, 't': 0.2})

    print(f'main process pid:{os.getpid()} running...')
    # 3.启动子进程
    sub_process_music.start()
    sub_process_speak.start()

    # 4.获取子进程的信息
    print('sub_process_music pid: %s' % sub_process_music.pid)
    print('sub_process_music name: %s' % sub_process_music.name)
    print('sub_process_music is_alive: %s' % sub_process_music.is_alive())  # True，当主进程调用join()方法后，该子进程消亡变为False
