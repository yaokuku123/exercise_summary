# 得到指定范围的全部素数
# 定义可迭代对象
class PrimeIterable(object):
    # 传入获取素数的初始和结束范围
    def __init__(self, start, end):
        self.start = start
        self.end = end

    # 调用iter()时候返回生成器函数
    def __iter__(self):
        for k in range(self.start, self.end + 1):
            # 判断是否为素数
            if self.is_prime(k):
                # yield返回素数
                yield k

    # 判断素数
    def is_prime(self, k):
        # 如果小于2不为素数
        # map函数获取从2到k-1的所有余数，all函数迭代map得到的结果，看是否有0，若有0表示不是素数直接返回False，否则最后返回True
        return False if k < 2 else all(map(lambda x: k % x, range(2, k)))


if __name__ == '__main__':
    p = PrimeIterable(1, 30)
    for item in p:
        print(item)
