a = [10,22,59]

try:
    print(a[6]/2)
    a[2]/0
except ZeroDivisionError:
    print("숫자는 0으로 나눌 수 없습니다.")
except IndexError : 
    print("해당 데이터는 a list안에 없습니다.")