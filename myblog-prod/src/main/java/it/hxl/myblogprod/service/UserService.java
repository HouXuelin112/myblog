package it.hxl.myblogprod.service;

import it.hxl.myblogprod.entity.Users;

import java.util.List;

public interface UserService {

    Users findUserById(int id);

    void updateUser(Users user);

    /**
     * 验证用户名密码，返回user实体
     * @param user
     * @return
     */
    Users validate(Users user);

    /**
     * 查找最近访客
     * @return
     */
    List<Users> findRecentVisitors();

    /**
     * 插入一个user到数据库
     * @param user
     */
    void insertUser(Users user);

}
