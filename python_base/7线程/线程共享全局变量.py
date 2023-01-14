import time
import threading

data_list = []


def write_data():
    for i in range(3):
        data_list.append(i)
    print(data_list)


def read_data():
    print(data_list)


if __name__ == '__main__':
    thread_write = threading.Thread(target=write_data)
    thread_read = threading.Thread(target=read_data)
    # 子线程1可以写入数据
    thread_write.start()
    time.sleep(1)
    # 子线程2可以正常读取子进程1向全局变量中写入的数据
    thread_read.start()
