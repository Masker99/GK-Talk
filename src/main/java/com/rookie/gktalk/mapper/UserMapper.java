package com.rookie.gktalk.mapper;

import com.rookie.gktalk.dto.UserDto;
import com.rookie.gktalk.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int insertUser(User user);
    User selectUser(String username,String email,String userID);
    int updateUser(User user);
    List<UserDto> selectUserList();
    int addScore(int userID,int score);
    int reduceScore(int userID,int score);
}
