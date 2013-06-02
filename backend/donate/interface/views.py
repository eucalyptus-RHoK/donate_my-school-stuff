from django.http import HttpResponse
from django.contrib.auth.decorators import login_required
from django.views.decorators.csrf import ensure_csrf_cookie
from django.shortcuts import render, render_to_response, redirect
from django.db.models import Q
from storage.models import *
import simplejson as json
from unipath import FSPath as Path
from donate.settings import MEDIA_ROOT
import time, base64


def user(request): # login & update user information
    try:
        data = request.POST or {}
        if data.has_key('userID') and int(data['userID']) > 0 : # update
            tmp = User.objects.get(pk=data['userID'])
        else: #try to login
            tmp = User.objects.get(username=data['username']) 
            if not tmp: # fallback new account
                tmp = User.objects.get(username=data['username'])
        tmp.location_lat = data['lat'] \
                if data.has_key('lat') else tmp.location_lat
        tmp.location_lon = data['lon'] \
                if data.has_key('lon') else tmp.location_lon
        tmp.contact = data['contact'] \
                if data.has_key('contact') else tmp.contact
        tmp.save()
        return HttpResponse(json.dumps({'userID':tmp.pk}), status=200)
    except Exception, e:
        return HttpResponse('Invalid query: '+str(e), status=500)


def search(request):
    resp = ''
    data = request.POST or {}
    ret = Obj.objects.all()
    try:
        if data.has_key('userID') and int(data['userID'])>0:
            ref = ret.filter(user__id=data['userID'])
        if data.has_key('searchstr'):
            for chunk in data['searchstr'].split(' '):
                ret = ret.filter(Q(name__contains=chunk) | \
                    Q(description__contains=chunk) | \
                    Q(tags=chunk))
        if data.has_key('searchcat') and int(data['searchcat']) > 0 :
            ret = ret.filter(category__pk=int(data['searchcat']))
        if data.has_key('searchschool') and int(data['searchschool']) > 0:
            ret = ret.filter(school__pk=int(data['searchschool']))

        resp = [dict(zip(['objectName', 'school', 'category', 'description', \
                'owner', 'owner_name', 'tag', 'picture'], p)) for p in \
                ret.select_related('owner__username').values_list(\
                'name', 'school__value', 'category__value', 'description', \
                'owner', 'owner__username', 'tags', 'picture').sort(\
                -last_mod)[0:20]]
        if len(resp) == 0 and data.has_key('userID'):
            ss = SuspendedSearch()
            ss.user = data['userID']
            if data.has_key('searchstr'):
                ss.searchstr = data['searchstr']
            if data.has_key('searchcat') and int(data['searchcat']) > 0 :
                ss.searchcat = int(data['searchcat'])
            if data.has_key('searchschool') and int(data['searchschool']) > 0:
                ss.searchschool = int(data['searchschool'])
            ss.save()
    except Exception, e:
        return HttpResponse('Invalid query: '+str(e), status=500)
    return HttpResponse(resp, status=200)

def publish(request):
    data = request.POST['publish']
    if data.has_key('delete') and data['delete']:
        try:
            tmp = Obj.objects.get(pk=int(data['objectPK']))
            if tmp.owner.pk == int(data['userID']):
                tmp.delete()
                return HttpResponse(status=200)
            else:
                return HttpResponse(status=403)
        except Exception, e:
            return HttpResponse(str(e), status=500)
    elif data.has_key('pk') and int(data['pk']) > 0:
        try: # update exists and if authorized
            tmp = Obj.objects.get(pk=int(data['objectPK']))
            if tmp.owner.pk != int(data['userID']):
                return HttpResponse(status=403)
        except Exception, e:
            return HttpResponse(status=404)
    else: # build new one
        tmp = Obj()

    tmp.name = data['objectName'] if data.has_key('objectName') else tmp.name
    tmp.tags = data['tags'] if data.has_key('tags') else tmp.tags
    tmp.school = int(data['school']) if data.has_key('school') else tmp.school
    tmp.category = int(data['category']) \
        if data.has_key('category') else tmp.category
    tmp.description = data['description'] \
        if data.has_key('description') else tmp.description
    tmp.location_lat = data['lat'] \
        if data.has_key('lat') else tmp.location_lat
    tmp.location_lon = data['lon'] \
        if data.has_key('lon') else tmp.location_lon
    if data.has_key('image') :
        tmp.picture = \
            save_img(int(data['userID']), data['image'], data['image_ext'])

    return HttpResponse(o.json, status=200)

def bootstrap(request):
    return HttpResponse(json.dumps({
        'categories' :
            [dict(zip(['pk','value'],p)) \
            for p in Category.objects.values_list('pk','value')],
        'schools' : 
            [dict(zip(['pk','value'],p)) \
            for p in School.objects.values_list('pk','value')]
    }), status=200)



################################################################################

# save an img -> returns file name
def save_img(obj_pk, img_base64, ext):
    img_name = '%s_%s.%s' % (obj_pk,time.time()*(10**3), ext)
    with open(MEDIA_ROOT.child(img_name), 'wb') as f:
        f.write(base64.decode(img_base64))
    return img_name
