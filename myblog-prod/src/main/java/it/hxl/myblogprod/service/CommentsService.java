package it.hxl.myblogprod.service;

import it.hxl.myblogprod.entity.Comments;

import java.util.List;

public interface CommentsService {
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
}