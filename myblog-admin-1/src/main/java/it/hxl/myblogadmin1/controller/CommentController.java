package it.hxl.myblogadmin1.controller;

import it.hxl.myblogadmin1.entity.Comments;
import it.hxl.myblogadmin1.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentsService commentsService;

    @RequestMapping("/comment/deleteAllChecked")
    private String deleteAllChecked(int[] checkbox){
        String ids = Arrays.toString(checkbox);
        ids = ids.substring(1, ids.length()-1);
        commentsService.deleteMutiComments(ids);
        return "redirect:/comment/1";
    }


    @RequestMapping("/comment/delete")
    @ResponseBody
    public String deleteComments(int id){
        commentsService.deleteComments(id);
        return "ok";
    }

    @RequestMapping("/comment/see")
    @ResponseBody
    public Comments seeComments(int id){
        return commentsService.findCommentsById(id);
    }

    @GetMapping({"/comment/{current}"})
    public String comment(@PathVariable(value = "current") int current, HttpServletRequest request){
        int pageSize = 10;
        if (current == 0){
            current = 1;
        }
        List<Comments> comments = commentsService.findCommentsByPage(pageSize, current);
        request.setAttribute("comments", comments);
        request.setAttribute("current", current);
        request.setAttribute("pages", size2Arr((int) Math.ceil(commentsService.findAllComments().size() / (float)pageSize)));
        return "comment";
    }

    private int[] size2Arr(int size){
        int[] arr = new int[size];
        for (int i = 0; i < size; i++){
            arr[i] = i + 1;
        }
        return arr;
    }
}
