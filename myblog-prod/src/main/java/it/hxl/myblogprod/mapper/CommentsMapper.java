package it.hxl.myblogprod.mapper;

import it.hxl.myblogprod.entity.Comments;
import it.hxl.myblogprod.mapper.providers.CommentProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentsMapper {
    /**
     * 插入评论
     * @param comments
     * @return
     */
    @InsertProvider(type = CommentProvider.class, method = "insertComment")
    @Transactional
    int insertComments(Comments comments);

    /**
     * 根据博客id查找所有评论并返回
     * @return
     */
    @Select("select * from Comments where blog_id = #{blogId} and parent_id=0 order by issueDate desc")
    @Results(id = "commentMapper", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "user_id", property = "user", one = @One(select = "it.hxl.myblogprod.mapper.UserMapper.findUserById")),
            @Result(column = "blog_id", property = "blog", one = @One(select = "it.hxl.myblogprod.mapper.BlogMapper.findBlogById")),
            @Result(column = "comment_content", property = "commentContent"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "issueDate", property = "issueDate"),
            @Result(column = "toUser", property = "toUser", one = @One(select = "it.hxl.myblogprod.mapper.UserMapper.findUserById")),
    })
    List<Comments> findAllCommentsByBlogId(int blogId);

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
