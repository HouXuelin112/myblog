package it.hxl.myblogprod.mapper;

import it.hxl.myblogprod.entity.Message;
import it.hxl.myblogprod.mapper.providers.MessageProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface MessageMapper {

    /**
     * 根据message查找message的id返回
     * @param message
     * @return
     */
    @InsertProvider(type = MessageProvider.class, method = "insertMessage")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Transactional
    int insertMessage(Message message);

    /**
     * 查找所有父留言，按时间倒序返回
     * @return
     */
    @Select("select * from message where parent_id = 0 order by issueDate desc")
    @Results(id = "messageMap", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "user_id", property = "user", one = @One(select = "it.hxl.myblogprod.mapper.UserMapper.findUserById")),
            @Result(column = "message_content", property = "messageContent"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "issueDate", property = "issueDate", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "to_user_id", property = "toUser", one = @One(select = "it.hxl.myblogprod.mapper.UserMapper.findUserById")),
    })
    List<Message> findAllParentMessages();

    /**
     * 根据父id查找子留言返回
     * @param parentId
     * @return
     */
    @Select("select * from message where parent_id = #{parentId} order by issueDate desc")
    @ResultMap("messageMap")
    List<Message> findAllChildMessageByParentId(int parentId);

    @Select("select * from message where id=#{id}")
    @ResultMap("messageMap")
    Message getSingle(int id);
}
