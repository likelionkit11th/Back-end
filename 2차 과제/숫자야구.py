import random

class Baseball:
    def random_num(self):
        random_numbers = []
        for i in range(3):
            random_numbers=random.sample(range(1, 10), 3)
        return random_numbers

    def enter(self, random_numbers):
        user_numbers = []
        for i in range(3):
            user_numbers.append(int(input("숫자를 입력하세요: ")))

        count_st = 0
        for i in range(3):
            if user_numbers[i] == random_numbers[i]:
                count_st += 1

        if count_st == 3:
            end = 1
        else:
            end = 0

        count_ba = 0
        for i in range(3):
            count_ba += user_numbers.count(random_numbers[i])
        return count_st, count_ba, end

bas = Baseball()
random_numbers = bas.random_num() # 무작위 3개
for i in range(9):
    count_st, count_ba, end = bas.enter(random_numbers) # 숫자 입력 3개
    count_ba-=count_st
    print('스트라이크:',count_st,'볼:',count_ba)
    if end == 1:
        print('성공')
        break