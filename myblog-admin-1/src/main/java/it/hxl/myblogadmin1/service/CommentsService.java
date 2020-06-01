package it.hxl.myblogadmin1.service;

import it.hxl.myblogadmin1.entity.Comments;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentsService {

    List<Comments> findCommentsByPage(int pageSize, int currentPage);

    List<Comments> findAllComments();

    Comments findCommentsById(int id);

    int deleteComments(int id);

    int deleteMutiComments(String ids);

}
