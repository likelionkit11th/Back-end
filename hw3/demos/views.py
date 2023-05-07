from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.

def Calc(request):
    # return HttpResponse("계산기 기능 구현 시작 이게 맞나")
    
    #데이터 받아오기
    
    num1 = request.GET.get('num1')
    num2 = request.GET.get('num2')
    operators = request.GET.get('operators')
    
    
    # 기능 
    if operators == '+':
        result = int(num1) + int(num2)
    elif operators == '-':
        result = int(num1) - int(num2)
    elif operators == '*':
        result = int(num1) * int(num2)
    elif operators == '/':
        result = int(num1) / int(num2)
    else:
        result = 0 

        
    
    return render(request, 'calc.html', {"result": result})


