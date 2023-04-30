from Game import Game

class PlayGame:

    def __init__(self):
        self.game = Game()
        self.is_computer_attack = False
        self.repeatRun()
        self.change()

    def repeatRun(self):
        i = 0
        for i in range(9):
            score = self.game.play()
            if score == 3 :
                if self.is_computer_attack == True:
                    print("수비 실패!")
                elif self.is_computer_attack == False: 
                    print("공격 성공!")
                break
            else: #모두 못맞췄을때
                if self.is_computer_attack == True:
                    print("수비 성공!")
                    
                elif self.is_computer_attack == False:
                    print("공격 실패!")
                    break
    
    def change(self):
        ans1 = input("계속해서 게임을 하시겠습니까?(y/n)")
        if ans1.lower() == 'y':
            ans2 = input("컴퓨터와 공/수 교환을 하시겠습니까?(y/n)")
            if ans2.lower() == 'y':
                self.is_computer_attack = True
            elif ans2.lower() == 'n':
                self.is_computer_attack = False

            self.repeatRun()

            return self.is_computer_attack
        
        elif ans1.lower() == 'n':
            print("게임을 종료합니다.")

        else: 
            print("y 또는 n을 입력하세요.")
        

if __name__ == "__main__":
    m = PlayGame()