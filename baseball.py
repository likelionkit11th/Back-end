import random

class User:
    def __init__(self):
        self.ballCount=0
        self.outCount=0
        self.strikeCount=0
        self.answer = ''

    def generateAnswer(self): #플레이어가 정답을 생성
        while True:
            try:
                print("플레이어의 숫자를 입력합니다.")
                tempAnswer = input()
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

    def selectNumber(self): 
        if isinstance(self,Player):
            print("-----플레이어가 정답을 입력합니다.-----\n")
        else:
            print("-----컴퓨터가 정답을 입력합니다.-----\n")
    def increaseStrike(self):
        self.strikeCount+=1
    def increaseBall(self):
        self.ballCount+=1
    def increaseOut(self):
        self.outCount+=1


        

class Player(User):
    def __init__(self) -> None:
        super().__init__()

    def selectNumber(self): #맞추기
        super().selectNumber()
        while(True):
            try:
                inputAnswer = str(input('>> '))
                self.handleException(inputAnswer)
                break
            except ValueError as e:
                print(e)
                
        return inputAnswer


class Computer(User):
    def __init__(self) -> None:
        super().__init__()

    def generateAnswer(self): # 컴퓨터가 정답을 생성 
        print("컴퓨터의 숫자는 랜덤으로 정해집니다.")
        hundreds,tens,units = random.sample(range(0,9),3)
        self.answer = str(hundreds)+str(tens)+str(units)

    def selectNumber(self): # 맞추기
        super().selectNumber()
        hundreds,tens,units = random.sample(range(0,9),3)
        inputAnswer = str(hundreds)+str(tens)+str(units)
        return inputAnswer

        

class Game():
    def __init__(self):
        self.p1 = Player()
        self.com1 = Computer()
        self.turn = True
        self.startGame()
        
    def startGame(self):
        print("---게임을 시작합니다---")
        self.p1.generateAnswer()
        self.com1.generateAnswer()

        while(True):
            if(self.turn): # Player Turn
                self.playerInputAnswer = self.p1.selectNumber()
                self.checkAnswer(self.playerInputAnswer, self.com1.answer,self.p1)
                self.printResult(self.p1)
                self.turn=False
            else: #Computer Turn
                self.comInputAnswer = self.com1.selectNumber()
                self.checkAnswer(self.comInputAnswer, self.p1.answer,self.com1)
                self.printResult(self.com1)
                self.turn = True
            
            if (self.checkEndGame(self.p1, self.com1)):
                break
            
    def printResult(self,player): #결과 출력
        result = ''
        if(player.strikeCount>0):
            result+=f'{player.strikeCount} 스트라이크 '
            
        if(player.ballCount>0):
            result+=f'{player.ballCount} 볼 '
            
        if(player.outCount>0):
            result += f'{player.outCount} 아웃 '
        print('\n'+result+'\n')


    def checkEndGame(self,player,computer): #끝났는지 체크
        if(player.strikeCount==3):
            print("플레이어가 승리 했습니다")
            print(f"정답은 Player : {player.answer}, Computer : {computer.answer} 였습니다!")
            return True
        elif(computer.strikeCount==3):
            print("컴퓨터가 승리 했습니다")
            print(f"정답은 Player : {player.answer}, Computer : {computer.answer} 였습니다!")
            return True
        else:
            return False
        
    def checkAnswer(self, inputAnswer, enemyAnswer, player): #답인지 체크
            player.initCount()
            for i in range(len(inputAnswer)):
                if(enemyAnswer.find(inputAnswer[i])!=-1): # 일단 있는지부터 검사
                    if(enemyAnswer[i]==inputAnswer[i]): #있는데 인덱스까지 같다면
                        player.increaseStrike()
                    else: #있기만 하면
                        player.increaseBall()
                else: #아예 없으면
                    player.increaseOut()


if __name__ == "__main__" :
    game = Game()