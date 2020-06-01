package it.hxl.myblogadmin1.service.impl;

import it.hxl.myblogadmin1.entity.Comments;
import it.hxl.myblogadmin1.mapper.CommentsMapper;
import it.hxl.myblogadmin1.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentsService")

public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsMapper commentsMapper;

    @Override
    public List<Comments> findCommentsByPage(int pageSize, int currentPage) {
        return commentsMapper.findCommentByPage(pageSize, currentPage-1);
    }

    @Override
    public List<Comments> findAllComments() {
        return commentsMapper.findAllComments();
    }

    @Override
    public Comments findCommentsById(int id) {
        return commentsMapper.findCommentsById(id);
    }

    @Override
    public int deleteComments(int id) {
        return commentsMapper.deleteComments(id);
    }

    @Override
    public int deleteMutiComments(String ids) {
        return commentsMapper.deleteMutiComments(ids);
    }
}
