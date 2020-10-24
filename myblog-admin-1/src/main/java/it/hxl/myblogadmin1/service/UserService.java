package it.hxl.myblogadmin1.service;

import it.hxl.myblogadmin1.entity.Users;

import java.util.List;

public interface UserService {
    List<Users> findUserByPage(int pageSize, int currentPage);
    Users findUserById(int id);
    int deleteUserById(int id);
    int updateUser(Users user);
    int getUserCount();
}
