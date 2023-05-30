from django.db import models
from django.contrib.auth import get_user_model

User = get_user_model()

# Create your models here.

class Post(models.Model):
    image = models.ImageField(verbose_name='이미지', null = True, blank = True ) # 사용자나 관리자나 field에 대한 이름을 지정
    content = models.TextField(verbose_name='내용')
    created_at = models.DateTimeField(verbose_name='작성일', auto_now_add=True) 
    view_Count = models.IntegerField(verbose_name='조회수', default = 0)
    writer = models.ForeignKey(to=User, on_delete=models.CASCADE, null = True, blank = True)
    

class Comment(models.Model):
    content = models.TextField(verbose_name='내용')
    created_at = models.DateTimeField(verbose_name='작성일',auto_now_add=True)
    post = models.ForeignKey(to='Post', on_delete=models.CASCADE)
    writer = models.ForeignKey(to=User, on_delete=models.CASCADE)