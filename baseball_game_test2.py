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
            result = self.compare_try(try_input)
            if result == "3S":
                print("정답입니다")
                break
            else:
                print(result)

    def compare_try(self, try_input):
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
        
class Start:
    def __init__(self):
        self.input = input("3자리 숫자를 입력하세요: ")

if __name__ == "__main__":
    game = Baseball()
    start = Start()
    game.play()
