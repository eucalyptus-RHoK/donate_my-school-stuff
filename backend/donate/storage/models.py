from django.db import models

class User(models.Model):
    username = models.CharField(max_length=255, verbose_name=('username'), null=False, blank=False)
    location_lat = models.DecimalField(max_digits=19, decimal_places=16, blank=True, null=True)
    location_lon = models.DecimalField(max_digits=19, decimal_places=16, blank=True, null=True)
    contact = models.CharField(max_length=255, verbose_name=('principal contact'), null=False, blank=False)

class Tag(models.Model):
    value = models.CharField(max_length=255, default='', blank=False, null=False)

class School(models.Model):
    value = models.CharField(max_length=255, default='', blank=False, null=False)

class Category(models.Model):
    value = models.CharField(max_length=255, default='', blank=False, null=False)

class Object(models.Model):
    name = models.CharField(max_length=255, verbose_name=('donation name'), null=False, blank=True)
    tags = models.ManyToManyField(Tag, verbose_name=('tags'))
    school = models.ForeignKey(School, verbose_name=('school'))
    cat = models.ForeignKey(Category, verbose_name=('category'))
    description = models.TextField(default='', null=False, blank=True)
    owner = models.ForeignKey(User, null=False)

