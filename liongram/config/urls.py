"""
URL configuration for config project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/4.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path, include

from posts.views import url_view, url_param, function_view, class_view, index



urlpatterns = [
    path('', index, name='index'),
    path("admin/", admin.site.urls),
    path('url/', url_view),
    path('url/<str:username>/', url_param),
    path('fbv/', function_view),
    path('cbv/', class_view.as_view(), name='cbv'),
    path('posts/',include('posts.urls')),
]
