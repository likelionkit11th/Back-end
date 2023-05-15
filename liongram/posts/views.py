from django.shortcuts import render, redirect
from django.http import HttpResponse, JsonResponse

from django.views.generic.list import ListView
from django.contrib.auth.decorators import login_required
from .models import Post


def index(request):
    return render(request, 'index.html')

def post_list_view(request):
    return render(request, 'posts/post_list.html')

@login_required
def post_create_view(request):
    if request.method == 'GET':
        return render(request, 'posts/post_form.html')
    else:
        image = request.FILES.get('image')
        content = request.POST.get('content')
        print(image)
        print(content)
        Post.objects.create(
            image=image,
            content=content,
            writer=request.user
        )
        return redirect('index')

def post_update_view(request, id):
    return render(request, 'posts/post_form.html')

def post_delete_view(request,id ):
    return render(request, 'posts/post_confirm_delete.html')

def post_detail_view(request,id ):
    return render(request, 'posts/post_detail.html')


def url_view(request):
    print('url_view()')
    data = {'ok' : '001', 'msg' : 'OK'}
    return HttpResponse('<h1>url_view<h1>')
    # return JsonResponse(data)
    

def url_param(request, username):
    print('url_param()')
    print(f'username : {username}')
    print(f"request.GET: {request.GET}")
    return HttpResponse(username)


def function_view(request):
    print(f"request.method : {request.method}")
    
    if request.method == 'GET':
        print(f"request.GET : {request.GET}")
    elif request.method == 'POST':
        print(f"request.POST : {request.POST}")
    
    return render(request, 'view.html')

class class_view(ListView):
    model = Post
    ordering = ['-id']
    template_name = "cbv_view.html"

