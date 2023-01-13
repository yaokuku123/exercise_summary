class Student(object):

    def __init__(self, name, age, address):
        self.name = name
        self.age = age
        self.address = address

    def __str__(self):
        return f'name: {self.name}, age: {self.age}, address: {self.address}'
