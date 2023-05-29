from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.viewsets import ModelViewSet
from rest_framework.response import Response
from rest_framework.decorators import api_view

from .models import Post
from .serializers import PostModelSerializer
class PostModelViewSet(ModelViewSet):
    queryset = Post.objects.all()
    serializer_class = PostModelSerializer


@api_view()
def calculator(request):
    
    num1 = request.GET.get('num1', 0)
    num2 = request.GET.get('num2', 0)
    operators = request.GET.get('operators')
    
    
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
        
    data={
        'type':'FBV',
        'result':result,
    }
    
    return Response(data)

class CalculatorAPIView(APIView):
    
    def get(self,request):
        num1 = request.GET.get('num1', 0)
        num2 = request.GET.get('num2', 0)
        operators = request.GET.get('operators')
        
        
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
            
        data={
            'type':'CBV',
            'result':result,
        }
        return Response(data)
    
    def post(self, request):
        data={
            'type':'CBV',
            'method':'POST',
            'result':0,
        }
        return Response(data)
# Create your views here.
