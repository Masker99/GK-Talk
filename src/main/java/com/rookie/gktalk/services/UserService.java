package com.rookie.gktalk.services;

import com.rookie.gktalk.pojo.User;

import java.util.Date;

public interface UserService {
    User addUser(String username,String password,String email,String sex);
    User selectUser(String username,String email,String userID);
    User selectUserByUserName(String username);
    User selectUserByEmail(String email);
    User selectUserByID(String ID);
    User updateUser(String username, String sex,String email,String pitcherPath,String password);
    String getUserAvatar(String username);
    User getUserFromToken(String token);
}
