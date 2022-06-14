from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
import unittest
# from django.test import  TestCase

class ErrorPagesTestCase(unittest.TestCase):
    def setUp(self) -> None:
        # 设置selenium使用chrome的无头模式
        chrome_options = Options()
        # 在启动浏览器时加入配置
        self.browser = webdriver.Chrome(options=chrome_options)

    def tearDown(self) -> None:
        self.browser.close()


    def test_room_not_exist(self):
        self.browser.get("http://127.0.0.1:8000/chat/notExistedRoom/testWrongUser")
        # 等待加载，最p多等待20秒
        self.browser.implicitly_wait(20)
        # 处理弹窗
        myalert = self.browser.switch_to.alert
        myalert.accept()
        # 获取文本
        err = self.browser.find_element(By.ID, "ErrorMsgBox").text
        self.assertEqual(err, "无效聊天室")

    def test_error_page_room_is_invalid(self):
        self.browser.get("http://127.0.0.1:8000/chat/testInvalidRoom/testWrongUser")
        # 等待加载，最p多等待20秒
        self.browser.implicitly_wait(20)
        # 处理弹窗
        myalert = self.browser.switch_to.alert
        myalert.accept()
        # 获取文本
        err = self.browser.find_element(By.ID, "ErrorMsgBox").text
        self.assertEqual(err,"聊天室状态错误")

    def test_error_page_room_is_valid_but_user_is_wrong(self):
        self.browser.get("http://127.0.0.1:8000/chat/testroom/testWrongUser")
        # 等待加载，最p多等待20秒
        self.browser.implicitly_wait(20)
        # 处理弹窗
        myalert = self.browser.switch_to.alert
        myalert.accept()
        # 获取文本
        err = self.browser.find_element(By.ID, "ErrorMsgBox").text
        self.assertEqual(err,"当前用户不在聊天室列表内")

    def test_error_page_room_is_valid_and_user_is_valid(self):
        self.browser.get("http://127.0.0.1:8000/chat/testroom/testuser")
        # 等待加载，最p多等待20秒
        self.browser.implicitly_wait(20)
        title_name = self.browser.title
        self.assertEqual(title_name,"Just Whiper With Someone")