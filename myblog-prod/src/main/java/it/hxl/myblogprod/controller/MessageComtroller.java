package it.hxl.myblogprod.controller;

import it.hxl.myblogprod.entity.Message;
import it.hxl.myblogprod.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageComtroller {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/leaveMessage")
    public int leaveMessage(@RequestBody Message message){
        return messageService.insertMessage(message);
    }
    @RequestMapping("/replyMessage")
    public int replyMessage(@RequestBody Message message){
        return messageService.insertMessage(message);
    }

}
