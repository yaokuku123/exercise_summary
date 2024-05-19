import threading

# 线程共享变量，存在线程安全问题
global_num = 0
# 互斥锁对象
mutex = threading.Lock()


def add_global_num():
    global global_num
    for i in range(1000000):
        # 加锁
        mutex.acquire()
        global_num += 1
        # 解锁
        mutex.release()


if __name__ == '__main__':
    thread1 = threading.Thread(target=add_global_num)
    thread2 = threading.Thread(target=add_global_num)
    thread1.start()
    thread2.start()
    # 等待线程执行完毕
    thread1.join()
    thread2.join()
    print(global_num)
