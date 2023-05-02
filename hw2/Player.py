from User import User

class Player(User):
    def __init__(self) -> None:
        super().__init__()

    def selectNumber(self): #맞추기
        while(True):
            print("\n-----플레이어가 정답을 입력합니다.-----\n")
            try:
                inputAnswer = str(input('>> '))
                self.handleException(inputAnswer)
                break
            except ValueError as e:
                print(e)
                
        return inputAnswer