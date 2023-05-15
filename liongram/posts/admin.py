from django.contrib import admin
from .models import Post, Comment
# Register your models here.
class CommentInline(admin.TabularInline): # 댓글 정렬이 가로로
    model = Comment #모델 설정
    extra = 5 # default 댓글 개수 설정 
    min_num = 3 # 최소 개수
    max_num = 5 # 최대 개수
    verbose_name = "댓글" #단수형
    verbose_name_plural = "댓글" #복수형
    
# class CommentInline(admin.StackedInline): # 댓글 정렬이 세로로
#     model = Comment #모델 설정
#     extra = 5 # default 댓글 개수 설정 
#     min_num = 3 # 최소 개수
#     max_num = 5 # 최대 개수
#     verbose_name = "댓글" #단수형
#     verbose_name_plural = "댓글" #복수형
    
class PostModelAdmin(admin.ModelAdmin):
    list_display = ('id', 'image', 'content', 'created_at', 'view_Count', 'writer') #보여질 필드 설정
    list_filter = ('created_at',) #필터 옵션
    search_fields = ('id','writer__username') #검색 가능 한 필드 설정
    search_help_text = "게시판 번호, 작성자 검색이 가능합니다." # 검색창 밑에 뜰 문장
    readonly_fields = ('created_at', ) # 올린 날짜 보이는데 readonly 로
    inlines = [CommentInline] #게시글에 댓글 보이도록 설정
    actions = ['make_published']
    
    def make_published(modeladmin, request, queryset):
        for item in queryset:
            item.content = '운영 규반 위정으로 인한 블라인드 처리'
            item.save()
            
        

admin.site.register(Post,PostModelAdmin)
# admin.site.register(Comment)

