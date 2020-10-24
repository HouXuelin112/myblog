package it.hxl.myblogprod.controller;

import it.hxl.myblogprod.entity.Comments;
import it.hxl.myblogprod.entity.Users;
import it.hxl.myblogprod.http.WrapMapper;
import it.hxl.myblogprod.http.Wrapper;
import it.hxl.myblogprod.service.CommentsService;
import it.hxl.myblogprod.sessionmanager.ChildMessageSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.Date;

@RestController
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @RequestMapping("/comment/leave")
    public Wrapper leave(@RequestBody Comments comments) {
        System.out.println(comments);
        int id = commentsService.insertComments(comments);
        System.out.println(id);
        Comments com = commentsService.getSingle(id);
        ChildMessageSessionManager.getSessions().forEach(session -> {
            try {
                session.getBasicRemote().sendObject(com);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
                WrapMapper.error(e.getMessage());
            }
        });
        return WrapMapper.ok();
    }

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
