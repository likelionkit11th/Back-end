class Student: #self는 무조건 있어야댐
    def __init__(self, name, age):
        self.name =name
        self.age = age
    def printInfo(self):
        print(f'제 이름은 {self.name}이고, 나이는{self.age}입니다')

class ForeignStudent(Student):
    def printEngInfo(self):
        print(f"name : {self.name} age : {self.age}")

you = ForeignStudent("Jamie", 22)
you.printInfo()
you.printEngInfo()
