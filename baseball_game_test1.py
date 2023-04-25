# 짜야할 코드 개요 정리
# 1. 사용자가 숫자 3개를 입력 -> input사용, 이때 0~9까지의 숫자를 넣어야 한다는 문장 제시
# print('0~9까지 숫자 3개를 입력하시오')
# 2. 입력받은 숫자가 랜덤으로 도출한 숫자와 맞는지 확인하는 과정이 필요
# 이때, 값과 위치 맞다면 strike, 값은 1~3번째 중에 있지만 위치가 맞지 않다면 ball
# 3. 공수 교대 -> 컴퓨터가 숫자 3개를 선택함 => random.rand
# 내가 맞춰야 하므로 input으로 숫자 3개를 넣음 
import random

class Baseball:
    def __init__(self):
        self.try_number = self.generate_try_number()
        self.num_attempts = 0

    def generate_try_number(self):
        # 0부터 9까지의 중복되지 않는 3자리 숫자를 무작위로 생성
        numbers = list(range(0, 10))
        try_number = ''
        while len(try_number) < 3:
            num = random.choice(numbers)
            try_number += str(num)
            numbers.remove(num)
        return try_number

    def play(self):
        print("숫자 야구 게임을 시작")
        while True:
            try_input = input("3자리 숫자를 입력하세요 (0부터 9까지의 숫자 입력): ")
            if not try_input.isdigit() or len(try_input) != 3:
                print("3자리 숫자를 입력하세요.")
                continue
            self.num_attempts += 1
            result = self.compare_guess(try_input)
            if result == "3S":
                print("정답입니다")
                break
            else:
                print(result)

    def compare_guess(self, try_input):
        strike = 0
        ball = 0
        for i in range(3):
            if try_input[i] == self.try_number[i]:
                strike += 1
            elif try_input[i] in self.try_number:
                ball += 1
        if strike == 3:
            return "3S"
        else:
            return f"{strike}S {ball}B"

if __name__ == "__main__":
    game = Baseball()
    game.play()
