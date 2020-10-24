package it.hxl.myblogprod.mapper;

import it.hxl.myblogprod.entity.Blog;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BlogMapper {

    /**
     * 获取非置顶的Blog
     * @return 返回Blog的list
     */
    @Select("select * from blog where isTop=0 and tag_id=#{tagId}")
    @Results(id = "blogMapper", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "filename", property = "filename"),
            @Result(column = "Author_id", property = "admin", one = @One(select = "it.hxl.myblogprod.mapper.AdminMapper.findAdminById")),
            @Result(column = "isTop", property = "isTop"),
            @Result(column = "isOriginal", property = "isOriginal"),
            @Result(column = "tag_id", property = "tag", one = @One(select = "it.hxl.myblogprod.mapper.TagMapper.findTagById")),
            @Result(column = "visitCount", property = "visitCount"),
            @Result(column = "commentsCount", property = "commentsCount"),
            @Result(column = "description", property = "description"),
            @Result(column = "displayImage", property = "displayImage", javaType = byte[].class, jdbcType = JdbcType.BINARY),
            @Result(column = "issueDate", property = "issueDate", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
    })
    List<Blog> findNotTopBlogsByTagId(int tagId);

    /**
     * 根据pageId查询blog
     * @param pageId
     * @return
     */
    @Select("select top (${pageId}*${pageSize}) * from blog order by isTop desc, isOriginal desc")
    @ResultMap("blogMapper")
    List<Blog> findBlogByPageId(int pageId, int pageSize);

    @Select("select count(*) from blog")
    int getCountBlogs();

    /**
     * 获取非置顶的Blog
     * @return 返回Blog的list
     */
    @Select("select * from blog where isTop=0 order by visitCount desc")
    @ResultMap("blogMapper")
    List<Blog> findNotTopBlogs();

    /**
     * 根据热度获取前几行
     * @param rows 为行数
     * @return 返回Blog的list
     */
    @Select("select top ${rows} * from blog order by visitCount desc")
    @ResultMap("blogMapper")
    List<Blog> findHotTopBlogs(@Param("rows") int rows);

    /**
     * 根据id获取Blog
     * @param id blog的id
     * @return 一个Blog实体
     */
    @Select("select * from blog where id=#{id}")
    @ResultMap("blogMapper")
    Blog findBlogById(int id);

    /**
     * 获取置顶的Blog并返回
     * @return Blog的list
     */
    @Select("select * from blog where isTop=1 order by visitCount desc")
    @ResultMap("blogMapper")
    List<Blog> findTopBlogs();

    @Select("select top 10 * from blog where isTop=1 order by visitCount desc")
    @ResultMap("blogMapper")
    List<Blog> findTop10Blogs();

    /**
     * 获取置顶的Blog并返回
     * @return Blog的list
     */
    @Select("select * from blog where isTop=1 and tag_id=#{tag_id}")
    @ResultMap("blogMapper")
    List<Blog> findTopBlogsByTagId(int tagId);

    /**
     * 模糊查询
     * @param text
     * @return
     */
    @Select("select * from blog where title like concat('%', #{text}, '%')")
    @ResultMap("blogMapper")
    List<Blog> findBlogsLike(String text);

}
