import random
from User import User

class Computer(User):
    def __init__(self):
        super().__init__()

    def generateAnswer(self): # 컴퓨터가 정답을 생성 
        print("\n컴퓨터의 숫자는 랜덤으로 정해집니다.")
        hundreds,tens,units = random.sample(range(0,9),3)
        self.answer = str(hundreds)+str(tens)+str(units)

    def selectNumber(self): # 맞추기
        print("\n-----컴퓨터가 정답을 입력합니다.-----\n")
        hundreds,tens,units = random.sample(range(0,9),3)
        inputAnswer = str(hundreds)+str(tens)+str(units)
        return inputAnswer