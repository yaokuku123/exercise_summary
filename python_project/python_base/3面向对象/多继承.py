class Car(object):
    def run(self):
        print('car running...')


class ElectricCar(Car):
    def run(self):
        print('electricCar running...')


class GasCar(Car):
    def run(self):
        print('gasCar running')


class MyCar(ElectricCar, GasCar):
    pass


if __name__ == '__main__':
    # 查看多态调用的链条 MyCar -> ElectricCar -> GasCar -> Car -> object
    print(MyCar.__mro__)
    print(MyCar.mro())
    myCar = MyCar()
    # electricCar running...
    myCar.run()
