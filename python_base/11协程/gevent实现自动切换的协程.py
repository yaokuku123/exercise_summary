import time
import gevent
from gevent import monkey
# 为阻塞相关方法打补丁，无需调用gevent的方法，自动包装
monkey.patch_all()


def task1(n):
    for i in range(n):
        print(f'task1 running {i}...')
        time.sleep(1)


def task2(n):
    for i in range(n):
        print(f'task2 running {i}...')
        time.sleep(1)


if __name__ == '__main__':
    # 实际上用的是一个线程，而当其中一个协程阻塞时会进行切换。
    # 但这种切换是非抢占的，若其中一个协程while死循环未存在阻塞，则不会切换协程
    t1 = gevent.spawn(task1, 10)
    t2 = gevent.spawn(task2, 10)
    t1.join()
    t2.join()