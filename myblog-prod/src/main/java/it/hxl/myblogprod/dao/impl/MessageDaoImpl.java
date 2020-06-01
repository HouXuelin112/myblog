package it.hxl.myblogprod.dao.impl;

import it.hxl.myblogprod.dao.MessageDao;
import it.hxl.myblogprod.entity.Message;
import it.hxl.myblogprod.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.Date;
import java.util.List;

@Repository("messageDao")
public class MessageDaoImpl implements MessageDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    @Transactional
    public int insertMessage(Message message) {
        String sql = "insert into message(user_id, message_content, parent_id, issueDate, to_user_id)" +
                "values(?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, message.getUser().getId());
            ps.setString(2, message.getMessageContent());
            ps.setInt(3, message.getParentId());
            ps.setTimestamp(4, new Timestamp(new Date().getTime()));
            ps.setInt(5, message.getToUser().getId());
            return ps;
        }, keyHolder);
        int pk = keyHolder.getKey().intValue();
        return pk;
    }

    @Override
    public List<Message> findAllParentMessages() {
        String sql = "select *, u.id as uid, u.nickname as uNickname, u.head as uHead, tu.id as tuid, tu.nickName as tuNickname, tu.head as tuHead" +
                " from message m, users u, users tu" +
                " where parent_id=0 and user_id=u.id and to_user_id=tu.id order by issueDate desc";
        List<Message> messages ;
        try{
            messages = jdbcTemplate.query(sql, messageRowMapper());
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return messages;
    }

    @Override
    public List<Message> findAllChildMessageByParentId(int parentId) {
        String sql = "select *, u.id as uid, u.nickname as uNickname, u.head as uHead, tu.id as tuid, tu.nickName as tuNickname, tu.head as tuHead" +
                " from message m, users u, users tu" +
                " where parent_id="+ parentId +" and user_id=u.id and to_user_id=tu.id";
        List<Message> messages ;
        try{
            messages = jdbcTemplate.query(sql, messageRowMapper());
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return messages;
    }

    private RowMapper<Message> messageRowMapper(){
        return new RowMapper<Message>() {
            @Override
            public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
                Message message = new Message();
                Users user = new Users();
                user.setId(rs.getInt("uid"));
                user.setNickName(rs.getString("uNickName"));
                user.setHead(rs.getBytes("uHead"));

                Users toUser = new Users();
                toUser.setId(rs.getInt("tuid"));
                toUser.setNickName(rs.getString("tuNickName"));
                toUser.setHead(rs.getBytes("tuHead"));

                message.setId(rs.getInt("id"));
                message.setUser(user);
                message.setMessageContent(rs.getString("message_content"));
                message.setIssueDate(rs.getTimestamp("issueDate"));
                message.setToUser(toUser);
                return message;
            }
        };
    }
}
