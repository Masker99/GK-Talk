package com.rookie.gktalk.controller;

import com.rookie.gktalk.pojo.User;
import com.rookie.gktalk.services.UserService;
import com.rookie.gktalk.utils.annotation.PassToken;
import com.rookie.gktalk.utils.annotation.UserLoginToken;
import com.rookie.gktalk.utils.bcrypt.BCryptPasswordEncoder;
import com.rookie.gktalk.utils.common.FileUtil;
import com.rookie.gktalk.utils.common.Result;
import com.rookie.gktalk.utils.common.StringUtil;
import com.rookie.gktalk.utils.common.TokenUtil;
import com.rookie.gktalk.utils.exception.WebException;
import com.rookie.gktalk.utils.validate.DataAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Object registerUser(@RequestBody Map<String,String> RequestBody) {
        String userName = RequestBody.get("username");
        String password = RequestBody.get("password");
        String email = RequestBody.get("email");
        String sex = RequestBody.get("sex");

        DataAssert.notEmpty(userName,"请输入用户名!");
        DataAssert.notEmpty(password,"请输入密码！");
        DataAssert.notEmpty(email,"请输入注册邮箱！");

        DataAssert.isTrue(StringUtil.isValid(userName,StringUtil.USERNAME_REGEX),"请输入正确格式的用户名！");
        DataAssert.isTrue(StringUtil.isValid(password,StringUtil.PASSWORD_REGEX),"请输入正确的密码！");
        DataAssert.isTrue(StringUtil.isValid(email,StringUtil.EMAIL_REGEX),"请输入正确的邮件！");

        DataAssert.isNull(userService.selectUserByUserName(userName),"该用户名已被注册！");
        DataAssert.isNull(userService.selectUserByEmail(email),"该邮件已被注册！");

        userService.addUser(userName,password,email,sex);

        return "注册成功!";
    }

    @PostMapping("/avatar")
    public Object avatarUpload(@RequestParam("file")MultipartFile multipartFile,HttpServletRequest request){
        String username = request.getParameter("username");
        String filePath = FileUtil.fileUploadAvatar(multipartFile,request,username);

        userService.updateUser(username,null,null,filePath,null);

        return "头像设置完成!";
    }

    @PostMapping("/login")
    public Object userLogin(@RequestBody Map<String,String> request){
        String username = request.get("username");
        String password = request.get("password");

        User user = userService.selectUserByUserName(username);
        if (user == null){
            throw new WebException("未查询到该用户！");
        }

        String passwordFromDB = user.getPassword();
        if(!new BCryptPasswordEncoder().matches(password,passwordFromDB)){
            throw new WebException("输入密码错误！");
        }

        String token = TokenUtil.getToken(user);

        return new Result(200,"登陆成功",token);
    }

    @PostMapping("/modifyUserPassword")
    public Object modifyUserInformation(@RequestBody Map<String,String> request){
        String username = request.get("username");
        String password = request.get("password");
        String rePassword = request.get("rePassword");

        if (!password.equals(rePassword))
            throw new WebException("新密码不一致！");

        DataAssert.isTrue(StringUtil.isValid(password,StringUtil.PASSWORD_REGEX),"请输入正确的密码！");

        userService.updateUser(username,null,null,null,new BCryptPasswordEncoder().encode(password));

        return "修改成功";
    }
}
