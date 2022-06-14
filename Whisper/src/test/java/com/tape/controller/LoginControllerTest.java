package com.tape.controller;

import com.tape.entity.User;
import com.tape.service.UserService;
import junit.framework.TestCase;

import java.util.List;
import java.util.Objects;

public class LoginControllerTest extends TestCase {

/*    UserService userService;
    User user;

    public void testLoginCheck(User user) {
        String name = user.getUserName();
        String password = user.getUserPass();
        List<User> userList = userService.listUser();
        for(User usertest:userList)
        {
            if(name==usertest.getUserName())//用户已注册
            {
                if(password==user.getUserPass())
                {
                    System.out.println("登陆成功");
                }
                else
                {
                    System.out.println("重新登录");
                    testLoginCheck(user);
                }
            }
            else
            {
                createUser(user);//不存在用户，注册用户
            }
        }
    }

    public void createUser(User user)
    {
        String email = user.getUserEmail();
        List<User> userList = userService.listUser();
        for(User usertest:userList)
        {
            if(email==usertest.getUserEmail())//此邮箱已注册
            {
                System.out.println("此邮箱以注册，重新注册");
                createUser(user);
            }
            else
            {
                userService.insert(user);//注册成功
                System.out.println("注册成功");}
        }
    }*/
}