package com.tape.controller;

import com.tape.entity.Article;
import com.tape.entity.Comment;
import com.tape.service.impl.ArticelServiceImpl;
import com.tape.service.impl.CommentServiceImpl;
import com.tape.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller("CommentController")
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentServiceImpl commentService;
    @Autowired
    ArticelServiceImpl articelService;
    @Autowired
    UserServiceImpl userService;

    List<String> badWords = new ArrayList<>();
    public List<String> badWordsList()
    {
        badWords.add("badword");
        badWords.add("kill");
        return badWords;
    }

    @RequestMapping("")
    public ModelAndView showComments(int userId, int articleId)
    {
        ModelAndView mv = new ModelAndView();
        Article article = articelService.getArticleByIdService(articleId);
        List<Comment> comments;
        badWords = badWordsList();
        if(userId == article.getArticleUserId())
        {
            comments=commentService.listCommentByArticleIdService(articleId);
            for(String badword : badWords){
                for(Comment comment : comments)
                {
                    comment.setCommentContent(comment.getCommentContent().replace(badword,"***"));
                }
            }
        }
        else
        {
            comments=commentService.getHaveReplyComment(articleId);
            for(String badword : badWords){
                for(Comment comment : comments)
                {
                    comment.setCommentContent(comment.getCommentContent().replace(badword,"***"));
                }
            }
        }
        mv.addObject("comments",comments);
        mv.addObject("userId",userId);
        mv.addObject("userName",userName(userId));
        mv.addObject("articleId",articleId);
        mv.setViewName("comments");
        return mv;
    }
    @RequestMapping("/reply")
    public ModelAndView replyComment(int userId, int commentId)
    {
        ModelAndView mv = new ModelAndView();
        Comment selectedComment = commentService.getCommentByIdService(commentId);
        Comment childComment = commentService.getChildById(commentId);
        Article article = articelService.getArticleByIdService(selectedComment.getCommentArticleId());
        badWords = badWordsList();
        if(childComment == null && userId == article.getArticleUserId())
        {
            for(String badword : badWords){
                selectedComment.setCommentContent(selectedComment.getCommentContent().replace(badword,"***"));
            }
            mv.addObject("selectedComment", selectedComment);
            mv.addObject("userId",userId);
            mv.addObject("userName",userName(userId));
            mv.setViewName("reply");
            return mv;
        }
        else
        {
            for(String badword : badWords){
                childComment.setCommentContent(childComment.getCommentContent().replace(badword,"***"));
            }
            for(String badword : badWords){
                selectedComment.setCommentContent(selectedComment.getCommentContent().replace(badword,"***"));
            }
            mv.addObject("selectedComment",selectedComment);
            mv.addObject("childComment",childComment);
            mv.addObject("userId",userId);
            mv.addObject("userName",userName(userId));
            mv.setViewName("replied");
            return mv;
        }
    }

    @RequestMapping("/reply/commit")
    public ModelAndView replyCommit(Comment comment)
    {
        System.out.println("replyCommit执行了");
        ModelAndView mv = new ModelAndView();
        commentService.insertService(comment);
        Comment selectedComment = commentService.getCommentByIdService(comment.getCommentPid());
        for(String badword : badWords)
        {
            comment.setCommentContent(comment.getCommentContent().replace(badword,"***"));
        }
        for(String badword : badWords){
            selectedComment.setCommentContent(selectedComment.getCommentContent().replace(badword,"***"));
        }
        mv.addObject("selectedComment",selectedComment);
        mv.addObject("childComment",comment);
        mv.addObject("userId",comment.getCommentUserId());
        mv.setViewName("replied");
        return mv;
    }

    @RequestMapping("/create")
    public ModelAndView createTape(Integer userId, Integer articleId)
    {
        System.out.println("createTapes执行了");
        ModelAndView mv = new ModelAndView();
        mv.addObject("userId",userId);
        mv.addObject("userName",userName(userId));
        mv.addObject("articleId",articleId);
        mv.setViewName("newComment");
        return mv;
    }

    @RequestMapping("/create/commit")
    public String createCommit(Comment comment)
    {
        System.out.println("createCommit执行了"+comment);
        commentService.insertService(comment);
        return "redirect:/comment?userId="+comment.getCommentUserId()+"&articleId="+comment.getCommentArticleId();
    }

    String userName(int userId)
    {
        return userService.getUserByIdService(userId).getUserName();
    }

}
