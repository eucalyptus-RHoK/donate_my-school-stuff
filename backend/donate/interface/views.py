from django.http import HttpResponse
from django.contrib.auth.decorators import login_required
from django.views.decorators.csrf import ensure_csrf_cookie
from django.shortcuts import render, render_to_response, redirect
import simplejson as json


def search(request):
    resp = ''
    return HttpResponse(resp, status=200)

def publish(request):
    try:
        data = json.loads(request.POST['data'])
    except Exception, e:
        return HttpResponse(str(e), status=500)
    if data.has_key('delete') and data['delete']:

        pass # Delete pk
        # 200 if deleted ok
        return HttpResponse(status=200)
        # 500 if something went wrong [like unauthorized]
        return HttpResponse(status=500)
    elif data.has_key('pk') and int(data['pk']) > 0:
        pass # updade object
    else:
        pass # create

# expect o = (new|update) object
    return HttpResponse(o.json, status=200)

def bootstrap(request):
    return json.dumps({
        'categories' :
            [dict(zip(['pk','value'],p)) \
            for p in Category.objects.values_list('pk','value')],
        'schools' : 
            [dict(zip(['pk','value'],p)) \
            for p in School.objects.values_list('pk','value')]
    })

