# property可以使得像属性一样使用方法

# 方式1：采用注解方式
class Goods(object):
    def __init__(self, ori_price):
        self.ori_price = ori_price
        self.discount = 0.8

    @property
    def price(self):
        print('get price...')
        return self.ori_price * self.discount

    @price.setter
    def price(self, val):
        print('set price...')
        self.ori_price = val

    @price.deleter
    def price(self):
        print('del price...')
        del self.ori_price


# 方式2：采用函数方式
class Goods2(object):
    def __init__(self, ori_price):
        self.ori_price = ori_price
        self.discount = 0.8

    def get_price(self):
        print('get price...')
        return self.ori_price * self.discount

    def set_price(self, val):
        print('set price...')
        self.ori_price = val

    def del_price(self):
        print('del price...')
        del self.ori_price

    PRICE = property(get_price, set_price, del_price)


if __name__ == '__main__':
    # 方式1：采用注解方式
    goods = Goods(100)
    # 向属性那样调用price()方法
    print(goods.price)
    # setter
    goods.price = 200
    print(goods.price)
    # deleter
    # del goods.price
    # print(goods.ori_price)  # AttributeError: 'Goods' object has no attribute 'ori_price'
    print('-' * 40)

    # 方式2：采用函数方式
    goods2 = Goods2(300)
    print(goods2.PRICE)
    goods2.PRICE = 400
    print(goods2.PRICE)
    # del goods2.PRICE
    # print(goods2.ori_price) # AttributeError: 'Goods2' object has no attribute 'ori_price'
