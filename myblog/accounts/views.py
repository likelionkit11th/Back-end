from django.shortcuts import render,redirect
from django.http import JsonResponse
from .forms import UserForm
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
            return redirect('accounts:signup')
        
    # form
           
        



def login_view(request):

    if request.method == 'GET':
        return render(request, 'accounts/login.html')
    elif request.method == 'POST':
        pass
