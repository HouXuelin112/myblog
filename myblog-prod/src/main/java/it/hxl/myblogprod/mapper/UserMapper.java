package it.hxl.myblogprod.mapper;

import it.hxl.myblogprod.entity.Users;
import it.hxl.myblogprod.mapper.providers.UserProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserMapper {


    @UpdateProvider(type = UserProvider.class, method = "updateUser")
    @Transactional
    void updateUser(Users user);


    @Select("select * from users where id = #{id}")
    Users findUserById(int id);

    /**
     * 根据用户名密码查找用户
     * @param username
     * @param password
     * @return
     */
    @Select("select top 1 * from users where username=#{username} and password=#{password}")
    Users findUserByUsernameAndPassword(String username, String password);

    /**
     * 根据邮箱和密码查找用户
     * @param email
     * @param password
     * @return
     */
    @Select("select top 1 * from users where email=#{email} and password=#{password}")
    Users findUserByEmailAndPassword(String email, String password);


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
    @Insert("insert into users(username, password, email, nickName, head) values(#{username},#{password},#{email},#{nickName},#{head})")
    @Transactional
    void insertUser(Users user);

    /**
     * 根据用户名查找用户并返回
     * @param email
     * @return
     */
    @Select("select top 1 * from users where email=#{email}")
    Users findUserByEmail(String email);
}
