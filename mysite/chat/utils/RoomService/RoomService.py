import sqlite3

class RoomService():


    def __init__(self,db_path='test.db'):
        self._DB_Name = db_path
        self._conn = sqlite3.connect(self._DB_Name,check_same_thread=False)
        self._cursor = None
        try:
            # 创建游标
            self._cursor = self._conn.cursor()
        except Exception as e:
            print(e)
            print("数据库游标创建失败")

    def __del__(self):
        self._conn.close()

    def findRoomByName(self,room_name):
        sql='''
        SELECT * FROM room WHERE room.roomname = '{0}';
        '''.format(room_name)
        self._cursor.execute(sql)
        rooms=self._cursor.fetchall()
        return rooms[0] if len(rooms) else ()

    def checkUserInRoomByRoomId(self,user_name,room_id):
        sql = '''
        SELECT * FROM roomuser WHERE username='{0}' AND roomid='{1}';
        '''.format(user_name,room_id)
        self._cursor.execute(sql)
        res = self._cursor.fetchall()
        print(res)
        return len(res) > 0

    def checkUserInRoomByRoomName(self,user_name,room_name):
        room = self.findRoomByName(room_name)
        if len(room)==0:
            return False
        else:
            room_id = room[0]
            return self.checkUserInRoomByRoomId(user_name,room_id)

if __name__ == '__main__':
    rs = RoomService()
    print(rs.checkUserInRoomByRoomName("a","testroom1"))