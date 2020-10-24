package it.hxl.myblogadmin1.mapper;

import it.hxl.myblogadmin1.entity.Message;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MessageMapper {

    @Select("select count(*) from message")
    int getMessageCount();

    @Select("select top ${pageSize} * from message where id>=(select max(id) from (select top (${pageSize}*${lastPage}+1) * from message order by id asc) temp)")
    @Results(id = "messageMapper", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "message_content", property = "messageContent"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "issueDate", property = "issueDate"),
            @Result(column = "user_id", property = "user", one = @One(select = "it.hxl.myblogadmin1.mapper.UserMapper.findUserById"))
    })
    List<Message> findMessageByPage(@Param("pageSize") int pageSize, @Param("lastPage") int lastPage);

    @Select("select * from message where id=#{id}")
    @ResultMap("messageMapper")
    Message findMessageById(int id);

    @Delete("delete from message where id=#{id}")
    @Transactional
    int deleteMessageById(int id);

    @Delete("delete from message where id in (${ids})")
    @Transactional
    int deleteMultiMessage(@Param("ids") String ids);
}
