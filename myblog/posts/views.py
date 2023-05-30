from django.shortcuts import render,redirect,get_object_or_404
from .models import Post
# Create your views here.


def index(request):
    post_list = Post.objects.all().order_by('-created_at')
    context={
        'post_list' : post_list,
    }
    return render(request,'index.html',context)

def post_create_view(request):
    if request.method =='GET':
        return render(request, 'posts/post_create.html')
    elif request.method =='POST':
        # print(request.POST)
        # print(request.POST.get('post-title'))
        post_title = request.POST.get('post-title')
        post_content = request.POST.get('post-content')
        Post.objects.create(
            title=post_title,
            content=post_content
        )
        return redirect('index')
    
def post_detail_view(request,id):
    try:
        post = Post.objects.get(id=id)
    except Post.DoesNotExist:
        redirect ('index')
    context={
        'post' : post,
    }
    return render(request,'posts/post_detail.html',context)

def post_edit_view(request,id):
    post = get_object_or_404(Post, id=id)
    if request.method == 'GET':
        context={
            'post':post
        }
        return render(request,'posts/post_create.html',context)
    elif request.method =='POST':
        new_title =request.POST.get('post-title')
        new_content = request.POST.get('post-content')
        post.title= new_title
        post.content = new_content
        post.save()
        return redirect('posts:post-detail',post.id)

def post_delete(request,id):
    post = get_object_or_404(Post, id=id)
    
    if request.method == 'POST':
        post.delete()
        return redirect('index')