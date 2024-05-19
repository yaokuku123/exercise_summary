import time
import multiprocessing


def do_task():
    for i in range(10):
        print('sub process running...')
        time.sleep(0.5)


if __name__ == '__main__':
    # 默认情况：主进程执行结束后当前程序不会结束，主进程会等待全部子进程结束后才会结束当前程序
    sub_process = multiprocessing.Process(target=do_task)
    # 设置子进程为守护进程，主进程结束后，当前子进程也会立即结束
    sub_process.daemon = True
    sub_process.start()
    time.sleep(1)
    print('main process over!')
    # sub_process.terminate()  # 与设置守护进程效果一致，通知该子进程结束
