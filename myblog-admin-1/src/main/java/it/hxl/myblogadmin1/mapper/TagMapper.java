package it.hxl.myblogadmin1.mapper;

import it.hxl.myblogadmin1.entity.Tag;
import it.hxl.myblogadmin1.mapper.provider.TagProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TagMapper {

    @Delete("delete from tags where id=#{id}")
    @Transactional
    int deleteTag(int id);

    @UpdateProvider(type = TagProvider.class, method = "updateTag")
    @Transactional
    int updateTag(Tag tag);

    @Insert("insert into tags(tagName, tagDescription) values(#{tagName}, #{tagDescription})")
    @Transactional
    int insertTag(Tag tag);

    /**
     * 查找所有标签
     * @return
     */
    @Select("select * from tags")
    List<Tag> findAllTags();

    @Select("select * from tags where id=#{id}")
    Tag findTagById(int id);

}
