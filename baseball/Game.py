from Computer import Computer
from Player import Player


class Game():
    def __init__(self):
        self.p1 = Player()
        self.com1 = Computer()
        self.turn = True
        self.startGame()
        
    def startGame(self):
        print("\n---게임을 시작합니다---\n")
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
        
        strike = 'S '
        ball = 'B '
        out = 'O '
        result = ''

        if(player.strikeCount>0):
            result+=f'{player.strikeCount} 스트라이크 '

        if(player.ballCount>0):
            result+=f'{player.ballCount} 볼 '

        if(player.outCount>0):
            result += f'{player.outCount} 아웃 '

        for i in range(3):
            if(i<player.strikeCount):
                strike+='🟡 '
            else:
                strike+='⚪ '
            if(i<player.ballCount):
                ball+='🟢 '
            else:
                ball+='⚪ '
            if(i<player.outCount):
                out+='🔴 '
            else:
                out+='⚪ '

        print('\n\n'+strike+'\n\n'+ball+'\n\n'+out)
        print('\n'+result+'\n')
        


    def checkEndGame(self,player,computer): #끝났는지 체크
        if(player.strikeCount==3):
            print("플레이어가 승리 했습니다! \n")
            print(f"정답은 Player : {player.answer}, Computer : {computer.answer} 였습니다!")
            return True
        elif(computer.strikeCount==3):
            print("컴퓨터가 승리 했습니다! \n")
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