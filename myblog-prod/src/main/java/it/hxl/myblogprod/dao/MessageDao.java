package it.hxl.myblogprod.dao;

import it.hxl.myblogprod.entity.Message;

import java.util.List;

public interface MessageDao {

    /**
     * 根据message查找message的id返回
     * @param message
     * @return
     */
    int insertMessage(Message message);

    /**
     * 查找所有父留言，按时间倒序返回
     * @return
     */
    List<Message> findAllParentMessages();

    /**
     * 根据父id查找子留言返回
     * @param parentId
     * @return
     */
    List<Message> findAllChildMessageByParentId(int parentId);

}
