package it.hxl.myblogadmin1.service;

import it.hxl.myblogadmin1.entity.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageService {
    int getMessageCount();
    List<Message> findMessageByPage(int pageSize, int lastPage);
    Message findMessageById(int id);
    int deleteMessageById(int id);
    int deleteMultiMessage(String ids);
}
