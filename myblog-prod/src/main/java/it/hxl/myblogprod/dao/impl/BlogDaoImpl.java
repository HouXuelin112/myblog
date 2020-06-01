package it.hxl.myblogprod.dao.impl;

import it.hxl.myblogprod.dao.BlogDao;
import it.hxl.myblogprod.entity.Admin;
import it.hxl.myblogprod.entity.Blog;
import it.hxl.myblogprod.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("blogDao")
public class BlogDaoImpl implements BlogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;



    @Override
    public List<Blog> findTopBlogs() {
        String sql = "select *, b.id as bid, t.id as tid, a.id as aid" +
                " from tags t, admin a, blog b " +
                "where isTop=1 and Author_id = a.id and tag_id = t.id";
        return getBlogListBySql(sql);
    }

    @Override
    public List<Blog> findTopBlogsByTagId(int tagId) {
        String sql = "select *, b.id as bid, t.id as tid, a.id as aid" +
                " from tags t, admin a, blog b " +
                "where isTop=1 and Author_id = a.id and tag_id = t.id and tag_id=" + tagId;
        return getBlogListBySql(sql);
    }

    @Override
    public List<Blog> findBlogsLike(String text) {
        String sql = "select * from blog where title like concat('%', '" + text + "', '%')";
        List<Blog> blogs;
        try{
            blogs = jdbcTemplate.query(sql, new RowMapper<Blog>() {
                @Override
                public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Blog blog = new Blog();
                    blog.setId(rs.getInt("id"));
                    blog.setTitle(rs.getString("title"));
                    return blog;
                }
            });
        }catch (Exception e){
            return null;
        }
        return blogs;
    }

    @Override
    public List<Blog> findNotTopBlogsByTagId(int tagId) {
        String sql = "select *, b.id as bid, t.id as tid, a.id as aid" +
                " from tags t, admin a, blog b " +
                "where isTop=0 and Author_id = a.id and tag_id = t.id and tag_id=" + tagId;
        return getBlogListBySql(sql);
    }

    @Override
    public List<Blog> findNotTopBlogs() {
        String sql = "select *, b.id as bid, t.id as tid, a.id as aid" +
                " from tags t, admin a, blog b " +
                "where isTop=0 and Author_id = a.id and tag_id = t.id";
        return getBlogListBySql(sql);
    }

    @Override
    public List<Blog> findHotTopBlogs(int rows) {
        String sql = "select top " + rows + " *, b.id as bid, t.id as tid, a.id as aid" +
                " from tags t, admin a, blog b " +
                "where Author_id = a.id and tag_id = t.id order by visitCount desc";
        List<Blog> blogs = getBlogListBySql(sql);
        return blogs;
    }

    @Override
    public Blog findBlogById(int id) {
        String sql = "select *, b.id as bid, t.id as tid, a.id as aid" +
                " from tags t, admin a, blog b " +
                "where b.id=" + id +" and Author_id = a.id and tag_id = t.id";
        return getBlogBySql(sql);
    }

    private List<Blog> getBlogListBySql(String sql){
        List<Blog> blogs;
        try {
            blogs = jdbcTemplate.query(sql, getRowMapper());
        }catch (Exception e) {
            return null;
        }
        return blogs;
    }

    private Blog getBlogBySql(String sql){
       Blog blog;
       try{
           blog = jdbcTemplate.queryForObject(sql, getRowMapper());
       }catch (Exception e){
           return null;
       }
        return blog;
    }


    private RowMapper<Blog> getRowMapper(){
        return new RowMapper<Blog>() {
            @Override
            public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
                Admin admin = new Admin();
                admin.setAdminName(rs.getString("adminName"));
                admin.setNickName(rs.getString("nickName"));
                admin.setRealName(rs.getString("realName"));
                Tag tag = new Tag();
                tag.setTagName(rs.getString("tagName"));
                Blog blog = new Blog(
                        rs.getInt("bid"),
                        rs.getString("title"),
                        rs.getString("filename"),
                        admin,
                        rs.getInt("isTop"),
                        rs.getInt("isOriginal"),
                        tag,
                        rs.getInt("visitCount"),
                        rs.getInt("CommentsCount"),
                        rs.getBytes("displayImage"),
                        rs.getTimestamp("issueDate")
                );
                return blog;
            }
        };
    }

}
