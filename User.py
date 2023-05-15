class User:
    def __init__(self):
        self.inputList = []

    def insert_input(self):
        while True:
            try:
                input_str = input("3개의 숫자를 공백으로 구분하여 입력하세요. : ")
                self.inputList = list(map(int, input_str.split()))
                if len(self.inputList) != 3:
                    raise ValueError("3개의 숫자를 입력하세요.")
                if len(set(self.inputList)) != 3:
                    raise ValueError("중복된 숫자가 있습니다.")
                if not all(num in range(10) for num in self.inputList):
                    raise ValueError("0~9 사이의 숫자를 입력하세요.")
            except ValueError as e:
                print(e)
                continue
            break
        return self.inputList
