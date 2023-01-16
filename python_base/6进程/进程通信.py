import multiprocessing
import time


def write_data(queue: multiprocessing.Queue):
    for i in range(10):
        # 向队列写数据
        queue.put(i)


def read_data(queue: multiprocessing.Queue):
    while True:
        time.sleep(0.5)
        # 若队列不空，则从队列拿数据
        if not queue.empty():
            data = queue.get()
            print(data)
        else:
            break


if __name__ == '__main__':
    # 创建队列对象
    queue = multiprocessing.Queue()
    process1 = multiprocessing.Process(target=write_data, args=(queue,))
    process2 = multiprocessing.Process(target=read_data, args=(queue,))
    process1.start()
    process2.start()
