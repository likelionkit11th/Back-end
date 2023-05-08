from django.contrib import admin
from .models import Post, Comment
# Register your models here.

class PostModelAdmin(admin.ModelAdmin):
    list_display = ('id', 'image', 'content', 'date', 'view_Count', 'writer')
    list_filter = ('date',)
    search_fields = ('id','writer__username')
    search_help_text = "게시판 번호, 작성자 검색이 가능합니다."
admin.site.register(Post,PostModelAdmin)
admin.site.register(Comment)