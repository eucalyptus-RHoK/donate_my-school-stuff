from django.db import models

class User(models.Model):
    username = models.CharField(max_length=255, verbose_name=('username'), null=False, blank=False)
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
    tags = models.ManyToManyField(Tag, verbose_name=('tags'))
    school = models.ForeignKey(School, verbose_name=('school'))
    cat = models.ForeignKey(Category, verbose_name=('category'))
    description = models.TextField(default='', null=False, blank=True)
    owner = models.ForeignKey(User, null=False)

    class Meta(object):
        verbose_name = 'object'
        verbose_name_plural = 'objects'

    def __unicode__(self):
        return '%s [%s]' % (self.name, [str(t) for t in self.tags.all()].join(','))
