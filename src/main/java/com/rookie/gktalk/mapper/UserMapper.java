package com.rookie.gktalk.mapper;

import com.rookie.gktalk.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int insertUser(User user);
    User selectUser(String username,String email,String userID);
    int updateUser(User user);
}
