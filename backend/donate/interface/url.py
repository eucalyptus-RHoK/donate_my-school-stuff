from django.conf.urls import patterns, url, include

urlpatterns = patterns('interface.views',
    (r'^search/$', 'search'),
    (r'^publish/$', 'publish'),
)
