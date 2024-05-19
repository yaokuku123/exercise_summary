# 实现一个连续浮点数发生器，支持正向和反向两种迭代方式
from decimal import Decimal


class FloatRange(object):
    def __init__(self, start, end, step):
        self.start = Decimal(str(start))
        self.end = Decimal(str(end))
        self.step = Decimal(str(step))

    def __iter__(self):
        t = self.start
        while t <= self.end:
            yield float(t)
            t += self.step

    def __reversed__(self):
        t = self.end
        while t >= self.start:
            yield float(t)
            t -= self.step


if __name__ == '__main__':
    fr = FloatRange(2, 6, 0.2)
    for i in fr:
        print(i)
    print('-' * 20)
    for i in reversed(fr):
        print(i)
