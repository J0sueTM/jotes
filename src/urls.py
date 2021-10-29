# file: src/urls.py
# date: October 29, 2021
# description: Jotes URL Configuration.
#
# note: The `urlpatterns` list routes URLs to views. For more information please see:
#        https://docs.djangoproject.com/en/3.2/topics/http/urls/

from django.contrib import admin
from django.urls import path

urlpatterns = [
  path('admin/', admin.site.urls),
]
