from django.shortcuts import render,redirect
from .forms import SignUpForm
from django.contrib.auth.forms import AuthenticationForm  
from django.contrib.auth import login
from users.models import User
# from django.contrib.auth.forms import UserCreationForm

def signup_view(request):
    
    if request.method == 'GET':
        form = SignUpForm 
        context = {
            "form":form,
        }
        return render(request, 'accounts/signup.html',context)
    
    elif request.method == 'POST':
        form = SignUpForm(request.POST)

        if form.is_valid():
            form.save()
            return redirect('index')
        else:
            context={
                "form":form
            }
            return render(request, 'accounts/signup.html')

# Create your views here.

def login_view(request):
    
    if request.method == 'GET' :
        form =AuthenticationForm()
        context={
            "form":form,
        }
        return render(request, 'accounts/login.html',context)
    elif request.method == 'POST':
        form = AuthenticationForm(request, data=request.POST)

        if form.is_valid():
            login(request,form.user_cache) # 로그인이 되었다면 user_cache에 그 정보가 들어갔을 것

            return redirect('index')
        else:
            context = {
                'form':form,
            }
            return render(request,'accounts/login.html',context)
        


        # username = request.POST.get('username')
        # if username =='' or username == None:
        #     pass
        # user= User.objects.get(username==username)
        # if user == None:
        #     pass
        # password = request.POST.get('password')