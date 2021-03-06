package it.hxl.myblogprod.service.impl;

import it.hxl.myblogprod.entity.Message;
import it.hxl.myblogprod.mapper.MessageMapper;
import it.hxl.myblogprod.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;


    @Override
    public Message getSingle(int id) {
        return messageMapper.getSingle(id);
    }

    @Override
    public List<Message> findTreeMessage() {
        List<Message> messages = messageMapper.findAllParentMessages();
        this.getChildren(messages);
        return messages;
    }

    private void getChildren(List<Message> parentMessages){
        if (parentMessages != null && parentMessages.size() > 0) {
            for (int i = 0; i < parentMessages.size(); i ++) {
                parentMessages.get(i).setChildMessages(messageMapper.findAllChildMessageByParentId(parentMessages.get(i).getId()));
                getChildren(parentMessages.get(i).getChildMessages());
            }
        }
    }

    @Override
    public int insertMessage(Message message) {
        message.setIssueDate(new Date());
        messageMapper.insertMessage(message);
        return message.getId();
    }

    @Override
    public List<Message> findAllMessages() {
        List<Message> parentMessage = messageMapper.findAllParentMessages();
        List<Message> childMessage;
        for (Message message: parentMessage){
            childMessage = messageMapper.findAllChildMessageByParentId(message.getId());
            message.setChildMessages(childMessage);
        }
        return parentMessage;
    }
}
