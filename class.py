class Student: #self는 무조건 있어야댐
    def __init__(self, name, age):
        self.name =name
        self.age = age
    def printInfo(self):
        print(f'제 이름은 {self.name}이고, 나이는{self.age}입니다')

you = Student('지우성', 24)
you.printInfo()