for i in range(1, 6): #range(start, stop, step) #step's defalut = 1
    for j in range(1, 6):
        if (j <= i):
            print("*", end = "") #end = "" : defalut = \n, 자동으로 줄을 바꾸는 print()를 줄을 바꾸지 못하도록 해주는 설정
    print()
