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
    public Users validate(Users user) {
//        System.out.println(userMapper.findRecentVisitor().size());
        Users newUser = userMapper.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
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
        if (userMapper.findUserByUsername(user.getUsername()) != null){
            throw new RuntimeException("用户名已存在！");
        }
        userMapper.insertUser(user);
    }
}
