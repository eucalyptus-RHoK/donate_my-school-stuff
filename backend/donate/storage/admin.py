from django.contrib import admin
from storage.models import *

class ObjAdmin(admin.ModelAdmin):
    pass
class UserAdmin(admin.ModelAdmin):
    pass
class TagAdmin(admin.ModelAdmin):
    pass
class SchoolAdmin(admin.ModelAdmin):
    pass
class CategoryAdmin(admin.ModelAdmin):
    pass


admin.site.register(Obj, ObjAdmin)
admin.site.register(User, UserAdmin)
admin.site.register(Tag, TagAdmin)
admin.site.register(School, SchoolAdmin)
admin.site.register(Category, CategoryAdmin)
