package com.rookie.gktalk.services;

import com.rookie.gktalk.dto.AuthorDto;
import com.rookie.gktalk.dto.UserDto;
import com.rookie.gktalk.pojo.User;

import java.util.List;

public interface UserService {
    User addUser(String username,String password,String email);
    User selectUser(String username,String email,String userID);
    User selectUserByUserName(String username);
    User selectUserByEmail(String email);
    User selectUserByID(String ID);
    User updateUser(String username, String sex,String email,String pitcherPath,String password);
    String getUserAvatar(String username);
    User getUserFromToken(String token);
    AuthorDto getAuthorDto(String author_id);
    List<UserDto> getUsers();
    int addScore(int userID,int score);
    int reduceScore(int userID,int score);
    int deleteUser(int userID);
}
