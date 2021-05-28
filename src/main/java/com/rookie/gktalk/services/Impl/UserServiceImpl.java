package com.rookie.gktalk.services.Impl;

import com.rookie.gktalk.dto.AuthorDto;
import com.rookie.gktalk.dto.UserDto;
import com.rookie.gktalk.mapper.UserMapper;
import com.rookie.gktalk.pojo.User;
import com.rookie.gktalk.services.UserService;
import com.rookie.gktalk.utils.bcrypt.BCryptPasswordEncoder;
import com.rookie.gktalk.utils.common.TokenUtil;
import com.rookie.gktalk.utils.exception.WebException;
import com.rookie.gktalk.utils.validate.DataAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User addUser(String username, String password, String email) {
        User user = new User();
        user.setName(username);
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

    @Override
    public User getUserFromToken(String token){
        String username = TokenUtil.getAudience(token);
        User user = userMapper.selectUser(username,null,null);
        DataAssert.notNull(user,"未能匹配到该用户!");

        return user;
    }

    @Override
    public AuthorDto getAuthorDto(String author_id){
        User user = userMapper.selectUser(null,null,author_id);
        AuthorDto authorDto = new AuthorDto();
        authorDto.setAuthor_id(user.getUserID());
        authorDto.setAuthor_name(user.getName());
        authorDto.setAuthor_picpath(user.getPicpath());

        return authorDto;
    }

    @Override
    public List<UserDto> getUsers(){
        return userMapper.selectUserList();
    }

    @Override
    public int addScore(int userID,int score){
        return userMapper.addScore(userID, score);
    }

    @Override
    public int reduceScore(int userID,int score){
        return userMapper.reduceScore(userID, score);
    }
}
