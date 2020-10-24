package it.hxl.myblogprod.mapper;

import it.hxl.myblogprod.entity.ReadTimes;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadTimesMapper {


    @Select("select * from read_times where macAddr=#{macAddr} and blog_id=#{blogId}")
    @Results(id = "readTimesMapper", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "blog_id", property = "blog", one = @One(select = "it.hxl.myblogprod.mapper.BlogMapper.findBlogById")),
            @Result(column = "macAddr", property = "macAddr")
    })
    ReadTimes findReadTimeByMacAddr(String macAddr, int blogId);

    @Insert("insert into read_times(blog_id, macAddr) values(#{blog.id}, #{macAddr})")
    int insertReadTimes(ReadTimes readTimes);
}
