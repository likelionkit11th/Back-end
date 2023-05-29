from django.shortcuts import render,redirect
import json
from django.http import JsonResponse
from .forms import UserForm
from django.contrib.auth import login,logout
from django.contrib.auth.forms import AuthenticationForm  
# Create your views here.

def signup_view(request):

    if request.method =='GET':
        form = UserForm()
        context={
            "form":form
        }
        return render(request, 'accounts/signup.html',context)
    
    elif request.method == 'POST':
        form = UserForm(request.POST)

        if form.is_valid():
            form.save()
            print(form)
            return redirect('index')
        else:
            error_data = form.errors.as_json()
            error_dict= json.loads(error_data)
            # error_messages = {field: [error[0].message for error in error_list] for field, error_list in error_dict.items()}
            print(error_dict)
            return render(request, 'accounts/signup.html', {"error_messages" : error_dict})           
        
def login_view(request):
    print("뭔데")

    if request.method == 'GET':
        form = AuthenticationForm()
        context ={
            "form":form
        }
        return render(request, 'accounts/login.html',context)
    
    elif request.method == 'POST':
        form = AuthenticationForm(request, data=request.POST)

        print(form.error_messages)

        if form.is_valid():
            login(request,form.user_cache)
            return redirect('index')
        else:
            context={
                'form':form,
            }
            return render(request, 'accounts/login.html', context)

def logout_view(request):

    if request.user.is_authenticated:

        logout(request)

    return redirect('index')
