from django.db import models

# Create your models here.

class Post(models.Model):
    title = models.CharField(max_length=50,verbose_name="제목", null = True, blank = True)
    content = models.TextField(verbose_name="내용")
    created_at = models.DateTimeField(verbose_name="작성일", auto_now_add=True)
    comment_count = models.IntegerField(verbose_name="댓글 수", default = 0)
    like_count = models.IntegerField(verbose_name="좋아요 수", default = 0)
    # def __str__(self):
    #     return self.title,self.content