package it.hxl.myblogprod.controller;

import it.hxl.myblogprod.sessionmanager.MySessionManager;
import it.hxl.myblogprod.entity.Message;
import it.hxl.myblogprod.http.WrapMapper;
import it.hxl.myblogprod.http.Wrapper;
import it.hxl.myblogprod.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.EncodeException;
import java.io.IOException;

@RestController
public class MessageComtroller {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/message/sendMessage")
    public Wrapper sendMessage(@RequestBody Message message) {
        System.out.println(message);
        int id = messageService.insertMessage(message);
        Message msg = messageService.getSingle(id);
        MySessionManager.getSessions().forEach(session -> {
            try {
                session.getBasicRemote().sendObject(msg);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        });
        return WrapMapper.ok();
    }

    @RequestMapping("/message/findTreeMessage")
    public Wrapper findTreeMessage() {
        return WrapMapper.ok(messageService.findTreeMessage());
    }

    @RequestMapping("/leaveMessage")
    public int leaveMessage(@RequestBody Message message){
        return messageService.insertMessage(message);
    }
    @RequestMapping("/replyMessage")
    public int replyMessage(@RequestBody Message message){
        return messageService.insertMessage(message);
    }

}
