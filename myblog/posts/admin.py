from django.contrib import admin
from .models import Post
# Register your models here.

class PostModelAdmin(admin.ModelAdmin):
    list_display = ('id', 'title', 'content', 'created_at') #보여질 필드 설정
    list_filter = ('created_at',) #필터 옵션
    search_fields = ('id','writer__username') #검색 가능 한 필드 설정
    search_help_text = "게시판 번호, 작성자 검색이 가능합니다." # 검색창 밑에 뜰 문장
    readonly_fields = ('created_at', ) # 올린 날짜 보이는데 readonly 로
    actions = ['make_published']
    
    def make_published(modeladmin, request, queryset):
        for item in queryset:
            item.content = '운영 규반 위정으로 인한 블라인드 처리'
            item.save()

admin.site.register(Post,PostModelAdmin)