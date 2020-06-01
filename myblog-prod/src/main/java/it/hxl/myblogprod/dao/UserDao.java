package it.hxl.myblogprod.dao;

import it.hxl.myblogprod.entity.Users;

import java.util.List;

public interface UserDao {
    /**
     * 根据用户名密码查找用户
     * @param username
     * @param password
     * @return
     */
    Users findUserByUsernameAndPassword(String username, String password);


    /**
     * 修改用户上次登录时间
     * @param userId
     */
    void updateLastAccessTime(int userId);

    /**
     * 查找最近访客
     * @return
     */
    List<Users> findRecentVisitor();

    /**
     * 向数据库中插入一条users数据
     * @param user
     */
    void insertUser(Users user);

    /**
     * 根据用户名查找用户并返回
     * @param username
     * @return
     */
    Users findUserByUsername(String username);
}
