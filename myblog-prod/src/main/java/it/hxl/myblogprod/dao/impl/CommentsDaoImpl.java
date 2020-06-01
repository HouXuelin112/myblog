package it.hxl.myblogprod.dao.impl;

import it.hxl.myblogprod.dao.CommentsDao;
import it.hxl.myblogprod.entity.Blog;
import it.hxl.myblogprod.entity.Comments;
import it.hxl.myblogprod.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("commentsDao")
public class CommentsDaoImpl implements CommentsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public int insertComments(Comments comments) {
        String sql = "insert into Comments(user_id, blog_id, comment_content, parent_id, issueDate, toUser) values(?, ?, ?, ?, ?, ?)";
        int row = jdbcTemplate.update(
                sql,
                comments.getUser().getId(),
                comments.getBlog().getId(),
                comments.getCommentContent(),
                comments.getParentId(),
                comments.getIssueDate(),
                comments.getToUser().getId()
        );
        return row;
    }




    @Override
    public List<Comments> findAllCommentsByBlogId(int blogId) {
        String sql = "select *, c.id as cid, u.id as uid, b.id as bid from Comments as c, users as u, blog as b " +
                "where blog_id= " + blogId + " and parent_id=0 and u.id = user_id and b.id = blog_id order by c.issueDate desc";
        List<Comments> comments = jdbcTemplate.query(sql, getMapper());
        return comments;
    }


    @Override
    public List<Comments> findCommentsByParentIdAndBlogId(int parentId, int blogId){
        String sql = "select *, c.id as cid, u.id as uid, b.id as bid from Comments as c, users as u, blog as b " +
                "where blog_id= " + blogId + " and parent_id= " + parentId + " and u.id = user_id and b.id = blog_id order by c.issueDate desc";
        List<Comments> comments = jdbcTemplate.query(sql, getMapper());
        return comments;
    }

    @Override
    public List<Comments> findChildCommentsByParentIdAndBlogId(int parentId, int blogId){
        String sql = "select *, c.id as cid, u.id as uid, u.nickName as uNickName, u.head as uHead, tu.id as tuid, tu.nickName as tuNickName, tu.head as tuHead, b.id as bid from Comments as c, users as u, users as tu, blog as b" +
                " where c.blog_id=" + blogId +" and parent_id= " + parentId +  " and u.id = c.user_id and b.id = c.blog_id and c.toUser = tu.id order by c.issueDate asc";
        List<Comments> comments = jdbcTemplate.query(sql, getChildMapper());
        return comments;
    }

    private RowMapper<Comments> getChildMapper(){
        return new RowMapper<Comments>() {
            @Override
            public Comments mapRow(ResultSet rs, int rowNum) throws SQLException {
                Users user = new Users();
                user.setId(rs.getInt("uid"));
                user.setNickName(rs.getString("uNickName"));
                user.setHead(rs.getBytes("uHead"));
                Users user1 = new Users();
                user1.setId(rs.getInt("tuid"));
                user1.setNickName(rs.getString("tuNickName"));
                user1.setHead(rs.getBytes("tuHead"));
                Blog blog = new Blog();
                blog.setId(rs.getInt("bid"));
                Comments comments = new Comments();
                comments.setUser(user);
                comments.setToUser(user1);
                comments.setBlog(blog);
                comments.setId(rs.getInt("cid"));
                comments.setCommentContent(rs.getString("comment_content"));
                comments.setIssueDate(rs.getTimestamp("issueDate"));
                comments.setChildHtml(rs.getString("childHtml"));
                return comments;
            }
        };
    }

    private RowMapper<Comments> getMapper(){
        return new RowMapper<Comments>() {
            @Override
            public Comments mapRow(ResultSet rs, int rowNum) throws SQLException {
                Users user = new Users();
                user.setId(rs.getInt("uid"));
                user.setNickName(rs.getString("nickName"));
                user.setHead(rs.getBytes("head"));
                Blog blog = new Blog();
                blog.setId(rs.getInt("bid"));
                Comments comments = new Comments();
                comments.setUser(user);
                comments.setBlog(blog);
                comments.setId(rs.getInt("cid"));
                comments.setCommentContent(rs.getString("comment_content"));
                comments.setIssueDate(rs.getTimestamp("issueDate"));
                comments.setChildHtml(rs.getString("childHtml"));
                return comments;
            }
        };
    }
}
