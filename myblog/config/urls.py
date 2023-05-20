from django.contrib import admin
from django.urls import path,include
from posts.views import index

urlpatterns = [
    path("",index,name='index'),
    path("posts/",include('posts.urls')),
    path("admin/", admin.site.urls),
]
