package com.rookie.gktalk.services.Impl;

import com.rookie.gktalk.mapper.UserMapper;
import com.rookie.gktalk.pojo.User;
import com.rookie.gktalk.services.UserService;
import com.rookie.gktalk.utils.bcrypt.BCryptPasswordEncoder;
import com.rookie.gktalk.utils.exception.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User addUser(String username, String password, String email,String sex) {
        User user = new User();
        user.setName(username);
        user.setSex(sex);
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(password));

        Date date = new Date();
        user.setRegister_time(date);
        user.setModify_time(date);

        userMapper.insertUser(user);

        return selectUserByUserName(user.getName());
    }

    @Override
    public User selectUser(String username,String email,String userID){
        return userMapper.selectUser(username,email,userID);
    }

    @Override
    public User selectUserByUserName(String username){
        return selectUser(username,null,null);
    }

    @Override
    public User selectUserByEmail(String email){
        return selectUser(null,email,null);
    }

    @Override
    public User selectUserByID(String userID){
        return selectUser(null,null,userID);
    }

    @Override
    public User updateUser(String username,String sex,String email,String pitcherPath,String password){
        User user = new User();
        user.setName(username);
        user.setSex(sex);
        user.setEmail(email);
        user.setModify_time(new Date());
        user.setPicpath(pitcherPath);
        user.setPassword(password);

        userMapper.updateUser(user);

        return selectUserByUserName(user.getName());
    }

    @Override
    public String getUserAvatar(String username){
        User user = userMapper.selectUser(username,null,null);

        if(user == null){
            throw new WebException("未能匹配该用户！");
        }

        return user.getPicpath();
    }
}
