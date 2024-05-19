import multiprocessing
import time

# 全局共享变量
data_list = []


def write_data():
    for i in range(3):
        data_list.append(i)
    print(data_list)


def read_data():
    print(data_list)


if __name__ == '__main__':
    # 创建两个子进程，分别执行写和读全局变量的操作
    sub_process_write = multiprocessing.Process(target=write_data)
    sub_process_read = multiprocessing.Process(target=read_data)
    # 主进程向全局共享变量写数据
    write_data()
    time.sleep(1)
    # 写子进程，首先，可以获取到之前主进程写的数据，并且写入自己的数据
    sub_process_write.start()
    time.sleep(1)
    # 读子进程，首先，可以读取到之前主进程写的数据，但无法获取到写子进程写入的数据
    sub_process_read.start()

    # 结论：当子进程启动后，会拷贝一份当前主进程的环境状态到自己本身的进程中，之后进程间相互独立，互不影响
