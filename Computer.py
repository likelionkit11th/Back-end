import random

class Computer:
    def __init__(self):
        self.inputList = []

    def random_input(self):
        while len(self.inputList) < 3 :
            num = random.randint(0,9)
            if num in self.inputList :
                continue
            self.inputList.append(num)
        return self.inputList
    



    

