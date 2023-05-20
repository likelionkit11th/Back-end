
from django.contrib import admin
from django.urls import path
from .views import post_create_view

app_name = "posts"

urlpatterns=[
    path('create/', post_create_view, name="post-create")
]