from django.urls import path
from . import views

urlpatterns = [
    path('upload_img', views.upload_img, name='upload_img'),
    path('insert_img', views.insert_img, name='insert_img'),
]
