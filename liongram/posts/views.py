from django.shortcuts import render, redirect, get_object_or_404
from django.http import HttpResponse, JsonResponse, Http404

from django.views.generic.list import ListView
from django.contrib.auth.decorators import login_required
from .models import Post
from .forms import PostBaseForm,PostCreateForm

def index(request):
    post_list = Post.objects.all().order_by('-created_at')
    context = {
        'post_list' : post_list,
    }
    return render(request, 'index.html', context)

def post_list_view(request):
    # post_list = Post.objects.all() # Post 모델 전체 데이터 조회
    post_list = Post.objects.filter(writer = request.user) # Post.writer 가 현재 로그인한 사용자와 동일한 경우
    context = {
        'post_list' : post_list,
    }
    
    return render(request, 'posts/post_list.html', context)

# @login_required
def post_create_view(request):
    if request.method == 'GET':
        return render(request, 'posts/post_form.html')
    else:
        print(request.POST)
        print(request.POST.get('content'))

        image = request.FILES.get('image')
        content = request.POST.get('content')
        Post.objects.create(
            image=image,
            content=content,
            writer=request.user
        )
        return redirect('index')

def post_create_form_view(request):
    if request.method == 'GET':
        form = PostCreateForm()
        context = {'form' : form}
        return render(request, 'posts/post_form2.html', context)
    else:
        form = PostCreateForm(request.POST, request.FILES)
        if form.is_valid():
            Post.objects.create(
                image=form.cleaned_data['image'],
                content=form.cleaned_data['content'],
                writer=request.user
            )
        else:
            pass
        return redirect('index')
    

def post_update_view(request, id):
    post = get_object_or_404(Post, id=id, writer=request.user)
    if request.method =='GET':
        context = {
            'post':post,
        }
        return render(request, 'posts/post_form.html',context)
    elif request.method =='POST':
        new_image = request.FILES.get('image')
        content = request.POST.get('content')
        
        if new_image:
            post.image.delete()
            post.image = new_image
            
        post.content = content
        post.save()
        return redirect('posts:post-detail',post.id)
    
@login_required
def post_delete_view(request,id ):
    post = get_object_or_404(Post, id=id)
    if request.user != post.writer:
        raise Http404("잘못된 접근입니다.")
    if request.method =='GET':
        context = {
            'post':post,
        }
        return render(request, 'posts/post_confirm_delete.html',context)
    elif request.method == 'POST':
        post.delete()
        return redirect('index')

def post_detail_view(request,id ):
    try:
        post = Post.objects.get(id=id)
    except Post.DoesNotExist:
            return redirect('index')
    context={
        'post' : post,
    }
    return render(request, 'posts/post_detail.html',context)


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

