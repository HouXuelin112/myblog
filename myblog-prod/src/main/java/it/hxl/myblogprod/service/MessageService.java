package it.hxl.myblogprod.service;

import it.hxl.myblogprod.entity.Message;

import java.util.List;

public interface MessageService {

    Message getSingle(int id);
    /**
     * 返回树状message
     * @return
     */
    List<Message> findTreeMessage();

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
