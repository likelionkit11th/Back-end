
from django.contrib import admin
from django.urls import path
from .views import post_create_view,post_detail_view,post_edit_view,post_delete

app_name = "posts"

urlpatterns=[
    path('create/', post_create_view, name="post-create"),
    path('<int:id>/',post_detail_view, name="post-detail"),
    path('<int:id>/edit',post_edit_view, name="post-edit"),
    path('<int:id>/delete', post_delete, name="post-delete"),
]