package it.hxl.myblogprod.dao.impl;

import it.hxl.myblogprod.dao.UserDao;
import it.hxl.myblogprod.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Users findUserByUsernameAndPassword(String username, String password) {
        String sql = "select * from users where username=? and password=?";
        Users user = null;
        try{
            user = jdbcTemplate.queryForObject(sql, getRowMapper(), username, password);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return user;
    }

    @Override
    @Transactional
    public void updateLastAccessTime(int userId) {
        String sql = "update users set lastAccessTime=? where id=?";
        jdbcTemplate.update(sql, new Date(), userId);
    }

    @Override
    public List<Users> findRecentVisitor() {
        String sql = "select * from users where lastAccessTime > (getdate() - 5)";
        List<Users> users;
        try{
            users = jdbcTemplate.query(sql, getRowMapper());
        }catch(Exception e){
            return null;
        }
        return users;
    }

    @Override
    @Transactional
    public void insertUser(Users user) {
        String sql = "insert into users(username, password, phone, email, nickName, head) values(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getPhone(), user.getEmail(), user.getNickName(),user.getHead());
    }

    @Override
    public Users findUserByUsername(String username) {
        String sql = "select * from users where username=?";
        Users user;
        try{
            user = jdbcTemplate.queryForObject(sql, getRowMapper(), username);
        }catch(Exception e){
            return null;
        }
        return user;

    }


    private RowMapper<Users> getRowMapper(){
        return new RowMapper<Users>() {
            @Override
            public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
                Users user = new Users(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("nickName"),
                        rs.getBytes("head"),
                        rs.getTimestamp("registerDate"),
                        rs.getTimestamp("lastAccessTime")
                );
                return user;
            }
        };
    }
}
