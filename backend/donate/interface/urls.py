from django.conf.urls import patterns, url, include

urlpatterns = patterns('interface.views',
    (r'^user/$', 'user'),
    (r'^search/$', 'search'),
    (r'^publish/$', 'publish'),
    (r'^bootstrap/$', 'bootstrap'),
)
