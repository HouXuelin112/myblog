package it.hxl.myblogprod.service;

import it.hxl.myblogprod.entity.Message;

import java.util.List;

public interface MessageService {

    /**
     * 向数据库中插入一条数据，返回主键
     * @param message
     * @return
     */
    int insertMessage(Message message);

    /**
     * 查找所有的Message
     * @return
     */
    List<Message> findAllMessages();

}
