package com.rookie.gktalk.controller.admin;

import com.rookie.gktalk.dto.UserDto;
import com.rookie.gktalk.services.Impl.UserServiceImpl;
import com.rookie.gktalk.utils.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/users")
    public Object getUserList(){
        List<UserDto> userList = userService.getUsers();
        return new Result(200,"成功获取用户列表",userList);
    }

    @PutMapping("/score/{userID}/{credits}/add")
    public Object addUserScore(@PathVariable("userID")int userId,
                                  @PathVariable("credits")int credits){
        userService.addScore(userId,credits);

        return new Result(200,"成功修改用户积分",null);
    }

    @PutMapping("/score/{userID}/{credits}/reduce")
    public Object reduceUserScore(@PathVariable("userID")int userId,
                                  @PathVariable("credits")int credits){
        userService.reduceScore(userId,credits);

        return new Result(200,"成功修改用户积分",null);
    }


}
