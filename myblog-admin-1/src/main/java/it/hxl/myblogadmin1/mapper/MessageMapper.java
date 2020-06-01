package it.hxl.myblogadmin1.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageMapper {

    @Select("select count(*) from message")
    int getMessageCount();

}
