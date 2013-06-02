from django.http import HttpResponse
from django.shortcuts import render, render_to_response, redirect


def index(request):
    return render(request, 'index.html')
