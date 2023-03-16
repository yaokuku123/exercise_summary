'''
外观模式

内容：为子系统中的一组接口提供一个一致的界面，外观模式定义了一个高层接口，这个
接口使得这一子系统更加容易使用

角色：
    外观（Facade）
    子系统（Subsystem）

'''


# 子系统（Subsystem）
class Cpu(object):
    def run(self):
        print('cpu run')

    def stop(self):
        print('cpu stop')


# 子系统（Subsystem）
class Memory(object):
    def run(self):
        print('memory run')

    def stop(self):
        print('memory stop')


# 子系统（Subsystem）
class Disk(object):
    def run(self):
        print('disk run')

    def stop(self):
        print('disk stop')


# 外观（Facade）
class Computer(object):
    def __init__(self):
        self.cpu = Cpu()
        self.memory = Memory()
        self.disk = Disk()

    def run(self):
        self.cpu.run()
        self.memory.run()
        self.disk.run()

    def stop(self):
        self.disk.stop()
        self.memory.stop()
        self.cpu.stop()


if __name__ == '__main__':
    computer = Computer()
    computer.run()
    print('-' * 20)
    computer.stop()
