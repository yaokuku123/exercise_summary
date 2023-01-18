class TestDemo(object):
    def __init__(self,name):
        self.name = name

    def __getattribute__(self, item):
        print(f'get attribute, item:{item}...')
        return super().__getattribute__(item)

    def __getattr__(self, item):
        print(f'get attr, item:{item}...')


if __name__ == '__main__':
    # 若获取的是存在的属性时，__getattribute__方法生效，__getattr__方法失效
    # 若获取不存在的属性时，若存在__getattribute__方法则调用该方法，__getattr__优先级低不被调用，
    # 但__getattribute__中return super().__getattribute__(item)，也会调用__getattr__方法
    test_demo = TestDemo('yorick')
    print(test_demo.name)
    print(test_demo.age)