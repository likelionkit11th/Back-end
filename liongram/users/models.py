from django.db import models
from django.contrib.auth.models import AbstractUser, UserManager as DjangoUserManager

class UserManager(DjangoUserManager):

    def _create_user(self, username, email, password, **extra_fields):

        if not email:
            raise ValueError('이메일은 필수 값입니다.')
        user = self.model(username=username, email=email, **extra_fields) #유저 생성
        user.set_password(password) #db에 비밀번호를 그대로 넣지 않고 해싱해서 넣는다 매우 필수인듯
        user.save() # 저장
        user.save(using=self._db)
        return user
        
    def create_user(self, username, email=None, password=None, **extra_fields):
        extra_fields.setdefault('is_staff', False)
        extra_fields.setdefault('is_superuser',False)
        return self._create_user( username, email, password, **extra_fields)
        
    def create_superuser(self, username, email=None, password=None, **extra_fields):
        extra_fields.setdefault('is_staff', True)
        extra_fields.setdefault('is_superuser',True)
        return self._create_user( username, email, password, **extra_fields)
        



class User(AbstractUser):
    phone = models.CharField(verbose_name="전화번호",max_length=11)

    objects = UserManager()


# class UserInfo(models.Model):
#     phone_sub = models.CharField(verbose_name='보조 전화번호',max_length=11)

#     user = models.ForeignKey(to='User', on_delete=models.CASCADE)
# # Create your models here.
