package it.hxl.myblogadmin1.service.impl;

import it.hxl.myblogadmin1.entity.Users;
import it.hxl.myblogadmin1.mapper.UserMapper;
import it.hxl.myblogadmin1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Users> findUserByPage(int pageSize, int currentPage) {
        return userMapper.findUserByPage(pageSize, currentPage - 1);
    }

    @Override
    public Users findUserById(int id) {
        return userMapper.findUserById(id);
    }

    @Override
    public int deleteUserById(int id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public int updateUser(Users user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int getUserCount() {
        return userMapper.getUserCount();
    }
}
