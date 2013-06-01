from django.conf.urls import patterns, url, include

urlpatterns = patterns('client.views',
    (r'^$', 'index'),
)
