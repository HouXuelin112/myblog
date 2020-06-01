package it.hxl.myblogprod.controller;

import it.hxl.myblogprod.entity.Comments;
import it.hxl.myblogprod.entity.Users;
import it.hxl.myblogprod.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @RequestMapping("/comment")
    public Users comment(@RequestBody Comments comments, HttpSession session){
        Users user = (Users) session.getAttribute("user");

//        user.setId(1);
        comments.setUser(user);
        comments.setIssueDate(new Date());
        int row = commentsService.insertComments(comments);
        return user;
    }

    @RequestMapping("/reply")
    public String reply(@RequestBody Comments comments, HttpSession session){
        Users user = (Users) session.getAttribute("user");
//        user.setId(2);
        comments.setUser(user);
        comments.setIssueDate(new Date());
        int row = commentsService.insertComments(comments);
        return "ok";
    }

}
