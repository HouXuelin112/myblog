package it.hxl.myblogadmin1.mapper;

import it.hxl.myblogadmin1.entity.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserMapper {

    @Select("select * from users where id = #{id}")
    Users findUserById(int id);

    /**
     * 根据用户名密码查找用户
     * @param username
     * @param password
     * @return
     */
    @Select("select * from users where username=#{username} and password=#{password}")
    Users findUserByUsernameAndPassword(String username, String password);


    /**
     * 修改用户上次登录时间
     * @param userId
     */
    @Update("update users set lastAccessTime=getdate() where id=#{userId}")
    @Transactional
    void updateLastAccessTime(int userId);

    /**
     * 查找最近访客
     * @return
     */
    @Select("select * from users where lastAccessTime > (getdate() - 5)")
    List<Users> findRecentVisitor();

    /**
     * 向数据库中插入一条users数据
     * @param user
     */
    @Insert("insert into users(username, password, phone, email, nickName, head) values(#{username},#{password},#{phone},#{email},#{nickName},#{head})")
    @Transactional
    void insertUser(Users user);

    /**
     * 根据用户名查找用户并返回
     * @param username
     * @return
     */
    @Select("select * from users where username=#{username}")
    Users findUserByUsername(String username);
}
