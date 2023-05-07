from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.

def Calc(request):
    # return HttpResponse("계산기 기능 구현 시작 이게 맞나")
    return render(request, 'calc.html')


