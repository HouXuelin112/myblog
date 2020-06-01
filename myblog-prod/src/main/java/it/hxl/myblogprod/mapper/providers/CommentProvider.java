package it.hxl.myblogprod.mapper.providers;

import it.hxl.myblogprod.entity.Comments;
import org.apache.ibatis.jdbc.SQL;

public class CommentProvider {
    public String insertComment(Comments comments){
        return new SQL(){
            {
                INSERT_INTO("Comments");
                if (comments.getUser() != null && comments.getUser().getId() != 0){
                    VALUES("user_id", "#{user.id}");
                }
                if (comments.getBlog() != null && comments.getBlog().getId() != 0){
                    VALUES("blog_id", "#{blog.id}");
                }
                if (comments.getCommentContent() != null){
                    VALUES("comment_content", "#{commentContent}");
                }
                VALUES("parent_id", "#{parentId}");
                if (comments.getIssueDate() != null){
                    VALUES("issueDate", "#{issueDate}");
                }
                if (comments.getToUser() != null && comments.getToUser().getId() != 0){
                    VALUES("toUser", "#{toUser.id}");
                }
            }
        }.toString();
    }
}
