from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.viewsets import ModelViewSet
from rest_framework.response import Response
from rest_framework.decorators import api_view


from rest_framework import generics,status

from .models import Post, Comment
from .serializers import PostBaseModelSerializer, PostListModelSerializer, PostCreateModelSerializer, PostRetrieveModelSerializer





# 게시글 목록
class PostListCreateView(generics.ListAPIView, generics.CreateAPIView):
    
    queryset = Post.objects.all()
    serializer_class = PostListModelSerializer
    
    def post(self, request, *args, **kwargs):
        return self.create(request, *args, **kwargs)
    
    
    def create(self, request, *args, **kwargs):
        serializer = self.get_serializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        # self.perform_create(serializer)
        if request.user.is_authenticated:
            serializer.save(writer=request.user)
        else:
            serializer.save()
            
            
        headers = self.get_success_headers(serializer.data)
        return Response(serializer.data, status=status.HTTP_201_CREATED, headers=headers)

# 게시글 상세, tnwjd
class PostRetrieveUpdateView(generics.RetrieveAPIView, generics.UpdateAPIView, generics.DestroyAPIView):
    queryset = Post.objects.all()
    serializer_class = PostRetrieveModelSerializer
    
    
# 게시글 수정


# 게시글 삭제
    
class PostModelViewSet(ModelViewSet):
    queryset = Post.objects.all()
    serializer_class = PostListModelSerializer

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
