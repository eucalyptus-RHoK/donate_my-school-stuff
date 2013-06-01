from django.db import models

class User(models.Model):
	username = models.CharField()
	place = models.CharField()
	contacts = models.CharField()
	
	
class Object(models.Model):
	objname = models.CharField()
	tags = models.ManyToManyField(Tag)
	school = models.ForeignKey(School)
	cat = models.ForeignKey(Category)	
	description = models.CharField()
	owner = models.ForeignKey(User)
	
class Tag(models.Model):
	value = models.CharField()
	
class School(models.Model):
	value = models.CharField()

class Category(models.Model):
	value = models.CharField()

