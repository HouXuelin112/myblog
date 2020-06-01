package it.hxl.myblogadmin1.service.impl;

import it.hxl.myblogadmin1.mapper.MessageMapper;
import it.hxl.myblogadmin1.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public int getMessageCount() {
        return messageMapper.getMessageCount();
    }
}
