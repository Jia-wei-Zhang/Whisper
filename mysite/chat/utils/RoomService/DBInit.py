import sqlite3


DB_Name = 'test.db'
Table_Name = 'STUDENT'
# 连接数据库，如果不存在则会在当前目录创建
conn = sqlite3.connect(DB_Name)
try:
    # 创建游标
    cursor = conn.cursor()
    # 创建STUDENT表的SQL语句，默认编码为UTF-8
    SQL = '''
      CREATE TABLE %s (
        SNO CHAR(10),
        SNAME VARCHAR(20) NOT NULL,
        PRIMARY KEY(SNO)
    )
        ''' % (Table_Name)
    # 创建数据库表
    cursor.execute(SQL)

    # 提交到数据库
    conn.commit()
    print('创建数据库表%s成功' % (Table_Name))
except Exception as e:
    print(e)
    # 回滚
    conn.rollback()
    print('创建数据库表%s失败' % Table_Name)
finally:
    # 关闭数据库
    conn.close()