from django.shortcuts import render,redirect
import json
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
            error_data = form.errors.as_json()
            error_dict= json.loads(error_data)
            # error_messages = {field: [error[0].message for error in error_list] for field, error_list in error_dict.items()}
            print(error_dict)
            return render(request, 'accounts/signup.html', {"error_messages" : error_dict})           
        



def login_view(request):

    if request.method == 'GET':
        return render(request, 'accounts/login.html')
    elif request.method == 'POST':
        pass
