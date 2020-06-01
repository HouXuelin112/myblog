package it.hxl.myblogprod.dao.impl;

import it.hxl.myblogprod.dao.TagDao;
import it.hxl.myblogprod.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("tagDao")
public class TagDaoImpl implements TagDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Tag> findAllTags() {
        String sql = "select * from tags";
        List<Tag> tags;
        try{
            tags = jdbcTemplate.query(sql, getRowMapper());
        }catch (Exception e){
            System.out.println("执行findAllTags()方法时发生错误：" + e.getMessage());
            return null;
        }
        return tags;
    }

    private RowMapper<Tag> getRowMapper(){
        return new RowMapper<Tag>() {
            @Override
            public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
                Tag tag = new Tag();
                tag.setTagName(rs.getString("tagName"));
                tag.setId(rs.getInt("id"));
                tag.setTagDescription(rs.getString("tagDescription"));
                return tag;
            }
        };
    }
}
