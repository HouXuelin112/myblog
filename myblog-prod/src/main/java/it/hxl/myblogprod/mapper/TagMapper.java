package it.hxl.myblogprod.mapper;

import it.hxl.myblogprod.entity.Tag;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper {

    /**
     * 查找所有标签
     * @return
     */
    @Select("select * from tags")
    List<Tag> findAllTags();

    @Select("select * from tags where id=#{id}")
    Tag findTagById(int id);

}
