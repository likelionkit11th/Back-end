from django.contrib import admin
from django.urls import path, include
from django.conf import settings
from django.conf.urls.static import static
from posts.views import url_view, url_param, function_view, class_view, index


urlpatterns = [
    path('', index, name='index'),
    path("admin/", admin.site.urls),
    path('url/', url_view),
    path('url/<str:username>/', url_param),
    path('fbv/', function_view),
    path('cbv/', class_view.as_view(), name='cbv'),
    path('posts/',include('posts.urls')),
    path('__debug__/', include('debug_toolbar.urls')),

]


    
urlpatterns+= static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)