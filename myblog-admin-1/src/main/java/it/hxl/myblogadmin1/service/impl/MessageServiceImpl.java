package it.hxl.myblogadmin1.service.impl;

import it.hxl.myblogadmin1.entity.Message;
import it.hxl.myblogadmin1.mapper.MessageMapper;
import it.hxl.myblogadmin1.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public int getMessageCount() {
        return messageMapper.getMessageCount();
    }

    @Override
    public List<Message> findMessageByPage(int pageSize, int currentPage) {
        return messageMapper.findMessageByPage(pageSize, currentPage - 1);
    }

    @Override
    public Message findMessageById(int id) {
        return messageMapper.findMessageById(id);
    }

    @Override
    public int deleteMessageById(int id) {
        return messageMapper.deleteMessageById(id);
    }

    @Override
    public int deleteMultiMessage(String ids) {
        return messageMapper.deleteMultiMessage(ids);
    }
}
