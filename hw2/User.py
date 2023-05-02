class User:
    def __init__(self):
        self.ballCount=0
        self.outCount=0
        self.strikeCount=0
        self.answer = ''

    def generateAnswer(self): #플레이어가 정답을 생성
        while True:
            try:
                print("플레이어의 숫자를 입력합니다.\n")
                tempAnswer = input(">> ")
                self.handleException(tempAnswer)
                self.answer = tempAnswer
                break
            except ValueError as e:
                print(e)
                
    def handleException(self,answer): #예외 처리
        if(len(answer)) != 3:
            raise ValueError("3자리 숫자를 입력해야 합니다. 다시 입력해주세요!")
        if(len(set(answer))) != 3:
            raise ValueError("중복된 숫자는 입력할 수 없습니다. 다시 입력해주세요!")
        if(not answer.isdigit()):
            raise ValueError("숫자가 아닌 값이 포함되어 있습니다. 다시 입력해주세요!")
        return True
    
    def initCount(self): #카운트 초기화
        self.strikeCount=0
        self.ballCount=0
        self.outCount=0

    def increaseStrike(self):
        self.strikeCount+=1
    def increaseBall(self):
        self.ballCount+=1
    def increaseOut(self):
        self.outCount+=1