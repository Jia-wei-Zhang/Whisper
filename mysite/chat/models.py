from django.db import models

class Room(models.Model):
    """聊天室信息"""
    roomName = models.CharField(max_length=256)
    status = models.IntegerField()
    date_added = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return self.roomName

class UserInRoom(models.Model):
    """在聊天室内的用户"""
    room = models.ForeignKey(Room,on_delete=models.CASCADE)
    user = models.CharField(max_length=256)
