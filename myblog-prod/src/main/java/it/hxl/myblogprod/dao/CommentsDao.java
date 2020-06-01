package it.hxl.myblogprod.dao;

import it.hxl.myblogprod.entity.Comments;

import java.util.List;

public interface CommentsDao {
    /**
     * 插入评论
     * @param comments
     * @return
     */
    int insertComments(Comments comments);

    /**
     * 根据博客id查找所有评论并返回
     * @return
     */
    List<Comments> findAllCommentsByBlogId(int blogId);

    /**
     * 根据
     * @param parentId
     * @param blogId
     * @return
     */
    List<Comments> findCommentsByParentIdAndBlogId(int parentId, int blogId);

    List<Comments> findChildCommentsByParentIdAndBlogId(int parentId, int blogId);
}
