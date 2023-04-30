from Computer import Computer
from User import User

class Game:

    def play(self):
        score = 0
        self.computer = Computer()
        self.computer.random_input()
        #print(self.computer.inputList)        
        self.user = User()
        self.user.insert_input()

        resultList = []

        for i in range(3):
            if self.computer.inputList[i] == self.user.inputList[i]:
                resultList.append("S")
                score += 1 
            elif self.computer.inputList[i] in self.user.inputList:
                resultList.append("B")
            else:
                resultList.append("O")
        print(resultList)
        return score

