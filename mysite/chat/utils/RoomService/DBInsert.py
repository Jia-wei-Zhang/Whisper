import sqlite3

DB_Name = 'test.db'
# 连接数据库，如果不存在则会在当前目录创建
conn = sqlite3.connect(DB_Name)
try:
    # 创建游标
    cursor = conn.cursor()
    # 向STUDENT表插入数据的SQL语句
    SQL = '''
          INSERT INTO STUDENT VALUES('2016081111','张三'),('2016081112','李四'),('2016081113','王五');
          '''
    # 插入数据
    cursor.execute(SQL)

    # 提交到数据库
    conn.commit()
    print('插入数据到表STUDENT成功')
except Exception as e:
    print(e)
    # 回滚
    conn.rollback()
    print('插入数据到表STUDENT失败')
finally:
# 关闭数据库
    conn.close()