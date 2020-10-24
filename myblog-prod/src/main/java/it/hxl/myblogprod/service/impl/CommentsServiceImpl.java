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
    public Comments getSingle(int id) {
        return commentsMapper.findCommentById(id);
    }

    @Override
    public List<Comments> findTreeCommentsByBlogId(int blogId) {
        List<Comments> parentComments = commentsMapper.findCommentsByParentIdAndBlogId(0, blogId);
        this.getChildren(parentComments, blogId);
        return parentComments;
    }

    private void getChildren(List<Comments> parentComments, int blogId){
        if (parentComments != null && parentComments.size() > 0) {
            for (int i = 0; i < parentComments.size(); i ++) {
                parentComments.get(i).setChildComments(commentsMapper.findChildCommentsByParentIdAndBlogId(parentComments.get(i).getId(), blogId));
                parentComments.get(i).setChildMessages(parentComments.get(i).getChildComments());
                getChildren(parentComments.get(i).getChildComments(), blogId);
            }
        }
    }

    @Override
    public int insertComments(Comments comments) {
        int row = commentsMapper.insertComments(comments);
        System.out.println(row);
        return comments.getId();
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
