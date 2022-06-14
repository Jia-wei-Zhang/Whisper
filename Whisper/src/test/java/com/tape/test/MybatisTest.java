package com.tape.test;

import com.tape.dao.IArticleDao;
import com.tape.dao.ICommentDao;
import com.tape.dao.IUserDao;
import com.tape.entity.Article;
import com.tape.entity.Comment;
import com.tape.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试mybatis的crud操作
 */
@WebAppConfiguration("src/main/resources")
public class MybatisTest extends BaseTest{

    @Autowired
    private IUserDao userDao;

    /**
     * 测试查询所有
     */


    @Test
    public void testFindAll(){
        //5.执行查询所有方法
        List<User> users = userDao.listUser();
        System.out.println(users.size());
        for(User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void print(){
/*        Pattern p=Pattern.compile("sb");
        Matcher m=p.matcher("什么人啊sb吧");
        StringBuffer sb=new StringBuffer();
        boolean result = m.find();
        while(result){//如果匹配成功就替换
            m.appendReplacement(sb, "**");
            result=m.find();//继续下一步匹配
        }*/
        List<String> badwords = new ArrayList<>();
        badwords.add("傻子");
        badwords.add("抄作业");
        badwords.add("aa");
        String test = "什么人啊,是傻子吧，能不能让我抄作业啊";
        for(String badword : badwords){
            test = test.replace(badword,"**");
        }
        System.out.println(test.toString());
/*        String test = "123abc,11傻子2222，22222抄作业ahhahah";
        test = test.replace("sb","**");
        System.out.println(test.toString());*/
    }
}
