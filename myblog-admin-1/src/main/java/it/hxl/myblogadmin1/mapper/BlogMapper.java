package it.hxl.myblogadmin1.mapper;

import it.hxl.myblogadmin1.entity.Blog;
import it.hxl.myblogadmin1.mapper.provider.BlogProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface BlogMapper {


    @Delete("delete from blog where id in (${deleteStr})")
    @Transactional
    int deleteBlogs(@Param("deleteStr") String deleteStr);

    @InsertProvider(type = BlogProvider.class, method = "insertBlog")
    @Transactional
    int insertBlog(Blog blog);

    @Select("select count(*) from blog")
    int getBlogCount();

    @Select("select * from blog")
    @Results(id = "blogMapper", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "filename", property = "filename"),
            @Result(column = "Author_id", property = "admin", one = @One(select = "it.hxl.myblogadmin1.mapper.AdminMapper.findAdminById")),
            @Result(column = "isTop", property = "isTop"),
            @Result(column = "isOriginal", property = "isOriginal"),
            @Result(column = "tag_id", property = "tag", one = @One(select = "it.hxl.myblogadmin1.mapper.TagMapper.findTagById")),
            @Result(column = "visitCount", property = "visitCount"),
            @Result(column = "commentsCount", property = "commentsCount"),
            @Result(column = "description", property = "description"),
            @Result(column = "displayImage", property = "displayImage", javaType = byte[].class, jdbcType = JdbcType.BINARY),
            @Result(column = "issueDate", property = "issueDate", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
    })
    List<Blog> findAllBlogs();

    /**
     * 根据id获取Blog
     * @param id blog的id
     * @return 一个Blog实体
     */
    @Select("select * from blog where id=#{id}")
    @ResultMap("blogMapper")
    Blog findBlogById(int id);
}
