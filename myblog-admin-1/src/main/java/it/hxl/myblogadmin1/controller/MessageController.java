package it.hxl.myblogadmin1.controller;

import it.hxl.myblogadmin1.entity.Comments;
import it.hxl.myblogadmin1.entity.Message;
import it.hxl.myblogadmin1.service.CommentsService;
import it.hxl.myblogadmin1.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/deleteAllChecked")
    private String deleteAllChecked(int[] checkbox){
        String ids = Arrays.toString(checkbox);
        ids = ids.substring(1, ids.length()-1);
        messageService.deleteMultiMessage(ids);
        return "redirect:/message/1";
    }


    @RequestMapping("/delete")
    @ResponseBody
    public String deleteComments(int id){
        messageService.deleteMessageById(id);
        return "ok";
    }

    @RequestMapping("/see")
    @ResponseBody
    public Message seeMessage(int id){
        return messageService.findMessageById(id);
    }

    @GetMapping({"/{current}"})

    public String comment(@PathVariable(value = "current") int current, HttpServletRequest request){
        int pageSize = 10;
        if (current == 0){
            current = 1;
        }
        List<Message> messages = messageService.findMessageByPage(pageSize, current);
        request.setAttribute("messages", messages);
        request.setAttribute("current", current);
        request.setAttribute("pages", size2Arr((int) Math.ceil(messageService.getMessageCount() / (float)pageSize)));
        return "message";
    }

    private int[] size2Arr(int size){
        int[] arr = new int[size];
        for (int i = 0; i < size; i++){
            arr[i] = i + 1;
        }
        return arr;
    }
}
