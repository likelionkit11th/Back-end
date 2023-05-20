from django.shortcuts import render,redirect
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
    return render(request,'posts/post_detail',context)