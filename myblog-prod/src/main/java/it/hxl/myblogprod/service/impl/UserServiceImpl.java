package it.hxl.myblogprod.service.impl;

import it.hxl.myblogprod.entity.Users;
import it.hxl.myblogprod.mapper.UserMapper;
import it.hxl.myblogprod.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public Users findUserById(int id) {
        return userMapper.findUserById(id);
    }

    @Override
    public void updateUser(Users user) {
        userMapper.updateUser(user);
    }

    @Override
    public Users validate(Users user) {
//        System.out.println(userMapper.findRecentVisitor().size());
        Users newUser = userMapper.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        newUser = newUser == null ? userMapper.findUserByEmailAndPassword(user.getUsername(), user.getPassword()) : newUser;
        if (newUser != null){//更新用户的lastAccessTime
            userMapper.updateLastAccessTime(newUser.getId());
        }
        return newUser;
    }

    @Override
    public List<Users> findRecentVisitors() {
        return userMapper.findRecentVisitor();
    }

    @Override
    public void insertUser(Users user) {
        if (userMapper.findUserByEmail(user.getEmail()) != null){
            throw new RuntimeException("该邮箱已注册！");
        }
        userMapper.insertUser(user);
    }
}
