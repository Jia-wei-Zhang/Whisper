# chat/views.py
from django.shortcuts import render

import chat.models
from chat.models import Room,UserInRoom
# from .utils.RoomService.RoomService import RoomService

# rs = RoomService()

def index(request):
    return render(request, 'chat/index.html')

def room(request, room_name, user_name):
    try:
        room_find = Room.objects.get(roomName=room_name)
    except chat.models.Room.DoesNotExist as e:
        room_find = None
    if room_find is None:
        return render(request,'chat/errorMsg.html',{
            'ErrMsg':"无效聊天室"
        })
    else:
        # print(room_find)
        room=room_find
        try:
            user_in_room=UserInRoom.objects.filter(room=room)
        except chat.models.UserInRoom.DoesNotExist as e:
            user_in_room=None


        if room.status != 1:
            return render(request, 'chat/errorMsg.html', {
                'ErrMsg': "聊天室状态错误"
            })

        in_room=False
        for person in user_in_room:
            if person.user == user_name:
                in_room = True
        if not in_room:
            return render(request, 'chat/errorMsg.html', {
                'ErrMsg': "当前用户不在聊天室列表内"
            })
    return render(request,'chat/room.html',{
        'room_name':room_name,
        'user_name':user_name
    })


