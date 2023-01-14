import time
import threading


def do_task():
    for i in range(10):
        print('thread running...')
        time.sleep(0.5)


if __name__ == '__main__':
    # 默认情况：主线程执行结束后当前程序不会结束，主线程会等待全部子线程结束后才会结束当前程序
    thread = threading.Thread(target=do_task)
    # 设置子线程为守护线程，主线程结束后，当前子线程也会立即结束
    thread.setDaemon(True)
    thread.start()
    time.sleep(1)
    print('main thread end...')
