package it.hxl.myblogprod.service.impl;

import it.hxl.myblogprod.entity.Comments;
import it.hxl.myblogprod.mapper.CommentsMapper;
import it.hxl.myblogprod.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentsService")
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsMapper commentsMapper;


    @Override
    public int insertComments(Comments comments) {
        return commentsMapper.insertComments(comments);
    }

    @Override
    public List<Comments> findAllCommentsByBlogId(int blogId) {
        List<Comments> comments = commentsMapper.findAllCommentsByBlogId(blogId);
        int parentId;
        List<Comments> childComments;
        for (Comments comment: comments){
            parentId = comment.getId();
            childComments = commentsMapper.findChildCommentsByParentIdAndBlogId(parentId, blogId);
            comment.setChildComments(childComments);
        }
        return comments;
    }
}
