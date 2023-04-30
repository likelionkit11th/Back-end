import random
import numpy as np

number = []
number = np.random.randint(0,9, size=3)

def makeAnswer():
    i = 0
    Alist = []
    while(i<3):
        answer = int(input("숫자를 입력해주세요: "))
        if(answer < 0 or answer > 9):
            print("0~9 사이의 수를 입력해 주세요")
            continue 

        if answer in Alist:
            print("중복된 수가 있습니다. 다시 입력해주세요.")   

        else:
            Alist.append(answer)      
            i += 1

    return Alist

def score(Alist, number):       
    strike_count = 0              
    ball_count = 0
    i = 0

    while i < 3:
        if Alist[i] == number[i]:   
            strike_count += 1         
            i += 1
        elif Alist[i] in number:    
            ball_count += 1           
            i += 1
        else:                         
            i += 1

    return strike_count, ball_count

# play
tries = 0

while 1:
    answer = makeAnswer()
    strike, ball = score(answer, number)
    print("{}Strike {}Ball ".format(strike, ball))

    if strike == 3:
        tries += 1
        break
    else:
        tries += 1

print("정답입니다 , 실행횟수 ".format(tries))
