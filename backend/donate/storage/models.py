from django.db import models

class User(models.Model):
    username = models.CharField(unique=True, max_length=255, verbose_name=('username'), null=False, blank=False)
    location_lat = models.DecimalField(max_digits=19, decimal_places=16, blank=True, null=True)
    location_lon = models.DecimalField(max_digits=19, decimal_places=16, blank=True, null=True)
    contact = models.CharField(max_length=255, verbose_name=('principal contact'), null=False, blank=False)

    class Meta(object):
        verbose_name = 'user'
        verbose_name_plural = 'users'

    def __unicode__(self):
        return '%s {%s}' % (self.username, self.contact)

class Tag(models.Model):
    value = models.CharField(max_length=255, default='', blank=False, null=False)

    class Meta(object):
        verbose_name = 'tag'
        verbose_name_plural = 'tags'

    def __unicode__(self):
        return self.value or 'Invalid'

class School(models.Model):
    value = models.CharField(max_length=255, default='', blank=False, null=False)

    class Meta(object):
        verbose_name = 'school'
        verbose_name_plural = 'schools'

    def __unicode__(self):
        return self.value or 'Invalid'

class Category(models.Model):
    value = models.CharField(max_length=255, default='', blank=False, null=False)

    class Meta(object):
        verbose_name = 'category'
        verbose_name_plural = 'categories'

    def __unicode__(self):
        return self.value or 'Invalid'

class Obj(models.Model):
    name = models.CharField(max_length=255, verbose_name=('donation name'), null=False, blank=True)
    tags = models.CharField(max_length=500, verbose_name=('tags'), null=False, blank=True, default="")
    school = models.ForeignKey(School, verbose_name=('school'), null=True, blank=True)
    category = models.ForeignKey(Category, verbose_name=('category'), null=True, blank=True)
    description = models.TextField(default='', null=False, blank=True)
    owner = models.ForeignKey(User, null=False)
    picture = models.CharField(max_length=50, verbose_name=('picture file name'), null=False, blank=True)
    location_lat = models.DecimalField(max_digits=19, decimal_places=16, blank=True, null=True)
    location_lon = models.DecimalField(max_digits=19, decimal_places=16, blank=True, null=True)
    last_mod = models.DateTimeField(auto_now=True,verbose_name=('last mod'))

    class Meta(object):
        verbose_name = 'object'
        verbose_name_plural = 'objects'

    def __unicode__(self):
        return '%s [%s]' % (self.name, self.tags)

    def obj(self):
        return {
            'object_pk' : self.pk,
            'object_name' : self.name,
            'school' : self.school__value or '',
            'category' : self.category__vale or '',
            'description' : self.description or '',
            'owner' : self.owner__name or '',
            'tags' : self.tags or '',
            'picture' : '%s/%s' % (MEDIA_ROOT, self.picture) \
                if self.picture else ''
        }

class SuspendedSearch(models.Model):
    user = models.ForeignKey(User, verbose_name=('user'))
    searchstr = models.CharField(max_length=255, null=True, default='')
    searchcat = models.IntegerField(null=True, default=None)
    searchschool = models.IntegerField(null=True, default=None)

    class Meta(object):
        verbose_name = 'suspended search'
        verbose_name_plural = 'suspended search'

    def __unicode__(self):
        
        return '%s [%s]' % (self.user, self.searchstr)
