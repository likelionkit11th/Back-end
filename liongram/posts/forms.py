from django.core.exceptions import ValidationError
from django import forms
from .models import Post

# class PostBaseForm(forms.Form):
#     CATEGORY_CHOICES = [
#         ('1', '일반'),
#         ('2', '계정'),
#     ]
#     image = forms.ImageField(label="이미지")
#     content = forms.CharField(label="내용",widget=forms.Textarea)
    # category = forms.ChoiceField(label="카테고리", choices=CATEGORY_CHOICES)


class PostBaseForm(forms.ModelForm):    
    class Meta:
        model = Post
        fields = '__all__'
        
class PostCreateForm(PostBaseForm):
    class Meta(PostBaseForm.Meta):
        fields = ['image','content']
        
        
    def clean_content(self):
        data = self.cleaned_data["content"]
        if "비속어" not in data:
            raise ValidationError("비속어는 사용하실 수 없습니다")

        # Always return a value to use as the new cleaned data, even if
        # this method didn't change it.
        return data