package com.tape.controller;

import com.tape.entity.Article;
import com.tape.entity.Comment;
import com.tape.service.ArticleService;
import com.tape.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller("ArticleController")
@RequestMapping("article")
public class ArticleController {
    @Autowired
    public ArticleService articleService;

    @Autowired
    public UserService userService;

    List<String> badWords = new ArrayList<>();
    public List<String> badWordsList()
    {
        badWords.add("badword");
        badWords.add("kill");
        return badWords;
    }

    @RequestMapping("")
    public ModelAndView showAll(int userId)
    {
        System.out.println("articlecontroller执行了");
        badWords = badWordsList();
        List<Article> articles = articleService.getArticleByUserIdService(userId);
        System.out.println(userId);
        for(String badword : badWords){
            for(Article article : articles){
                article.setArticleTitle(article.getArticleTitle().replace(badword,"***"));
            }
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("articles",articles);
        mv.addObject("userId",userId);
        mv.addObject("userName",userName(userId));
        mv.addObject("who","我的");
        mv.setViewName("articles");
        return mv;
    }

    @RequestMapping("create")
    public ModelAndView create(int userId)
    {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userId",userId);
        mv.addObject("userName",userName(userId));
        mv.setViewName("create");
        return mv;
    }
    @RequestMapping("commit")
    public String createCommit(Article article)
    {
        //articleService.insert(article);
        System.out.println("创建成功"+article.getArticleUserId());
        articleService.insertService(article);
        return "redirect:/article?userId="+article.getArticleUserId();
    }

    @RequestMapping("all")
    public ModelAndView showOthers(int userId)
    {
        ModelAndView mv =new ModelAndView();
        List<Article> articles = articleService.listAllNotWithContent();
        badWords = badWordsList();
        for(String badword : badWords)
        {
            for(Article article : articles)
            {
                article.setArticleTitle(article.getArticleTitle().replace(badword,"***"));
            }
        }
        mv.addObject("articles",articles);
        mv.addObject("userId",userId);
        mv.addObject("userName",userName(userId));
        mv.addObject("who","全部的");
        mv.setViewName("articles");
        return mv;
    }

    String userName(int userId)
    {
        return userService.getUserByIdService(userId).getUserName();
    }
}
