package it.hxl.myblogadmin1.mapper;


import it.hxl.myblogadmin1.entity.Comments;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentsMapper {


    @Delete("delete from Comments where id in (${ids})")
    @Transactional
    int deleteMutiComments(@Param("ids") String ids);

    @Delete("delete from Comments where id=#{id}")
    @Transactional
    int deleteComments(int id);

    /**
     * 根据博客id查找所有评论并返回
     * @return
     */
    @Select("select * from Comments where blog_id = #{blogId} and parent_id=0 order by issueDate desc")
    @Results(id = "commentMapper", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "user_id", property = "user", one = @One(select = "it.hxl.myblogadmin1.mapper.UserMapper.findUserById")),
            @Result(column = "blog_id", property = "blog", one = @One(select = "it.hxl.myblogadmin1.mapper.BlogMapper.findBlogById")),
            @Result(column = "comment_content", property = "commentContent"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "issueDate", property = "issueDate"),
            @Result(column = "toUser", property = "toUser", one = @One(select = "it.hxl.myblogadmin1.mapper.UserMapper.findUserById")),
    })
    List<Comments> findAllCommentsByBlogId(int blogId);

    @Select("select * from Comments where id=#{id}")
    @ResultMap(value = "commentMapper")
    Comments findCommentsById(int id);


    @Select("select * from Comments")
    @ResultMap(value = "commentMapper")
    List<Comments> findAllComments();



    @Select("select top ${pageSize} * from Comments where id>=(select max(id) from (select top (${pageSize}*${lastPage}+1) * from Comments order by id asc) temp)")
    @ResultMap("commentMapper")
    List<Comments> findCommentByPage(@Param("pageSize") int pageSize, @Param("lastPage") int lastPage);

    /**
     * 根据
     * @param parentId
     * @param blogId
     * @return
     */
    @Select("select * from Comments where blog_id= #{blogId} and parent_id= #{parentId}")
    @ResultMap("commentMapper")
    List<Comments> findCommentsByParentIdAndBlogId(int parentId, int blogId);

    @Select("select * from Comments where blog_id= #{blogId} and parent_id= #{parentId} order by issueDate asc")
    @ResultMap("commentMapper")
    List<Comments> findChildCommentsByParentIdAndBlogId(int parentId, int blogId);
}
