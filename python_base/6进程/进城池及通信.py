import multiprocessing
import time


def write_data(queue):
    for i in range(10):
        # 向队列写数据
        queue.put(i)


def read_data(queue):
    # 获取队列中元素数量
    for i in range(queue.qsize()):
        # 获取队列中的数据
        print(queue.get())


if __name__ == '__main__':
    # 创建进程池，可以传参指定进程池的进程数量
    pool = multiprocessing.Pool()
    # 创建队列对象，进程池独有的队列
    queue = multiprocessing.Manager().Queue()
    # 启动进程，向队列写数据
    pool.apply_async(write_data, (queue,))
    time.sleep(0.5)
    # 启动进程，从队列中读数据
    pool.apply_async(read_data, (queue,))

    # 进程池的方式启动，主进程不会向等待全部任务运行结束，会直接结束，所以需要下面两句话
    # 关闭进程池，表示不再接收新的任务，已经进行中的任务与在排队的任务不受影响
    pool.close()
    # 主进程阻塞等待全部进程完成任务，join有回收子进程资源的作用（重点）
    pool.join()
