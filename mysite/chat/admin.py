from django.contrib import admin
from chat.models import Room,UserInRoom
admin.site.register(Room)
admin.site.register(UserInRoom)