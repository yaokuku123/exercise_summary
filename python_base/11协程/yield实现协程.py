import time


def task1():
    while True:
        print('task1 running...')
        yield
        time.sleep(1)


def task2():
    while True:
        print('task2 running...')
        yield
        time.sleep(1)


def run():
    t1 = task1()
    t2 = task2()
    while True:
        next(t1)
        next(t2)


if __name__ == '__main__':
    # 协程在Python中实际是在用户级完成切换，而均共享一个线程的使用，
    # 存在的是N：1的关系。由于线程在单核运行，所以导致协程也是无法切换核心
    run()
