
from django.contrib import admin
from django.urls import path, include
from rest_framework import routers
from posts.views import PostListCreateView, PostRetrieveUpdateView, PostModelViewSet, calculator,CalculatorAPIView

router = routers.DefaultRouter()
router.register('posts',PostModelViewSet)
# router.register('comments ',CommentHyperlinkedModelSerializer)

urlpatterns = [
    path("", include(router.urls)),
    # path('posts/',PostListCreateView.as_view(),name='post-list-create'),
    # path('posts/<int:pk>',PostRetrieveUpdateView.as_view(), name='post-detail'),
    path("admin/", admin.site.urls),
    # path('calculator/',calculator, name="calculator-fbv"),
    path('calculator/',CalculatorAPIView.as_view(), name="calculator-cbv"),
    
]
