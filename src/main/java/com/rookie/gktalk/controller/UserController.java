package com.rookie.gktalk.controller;

import com.rookie.gktalk.pojo.User;
import com.rookie.gktalk.services.Impl.UserServiceImpl;
import com.rookie.gktalk.utils.annotation.UserLoginToken;
import com.rookie.gktalk.utils.bcrypt.BCryptPasswordEncoder;
import com.rookie.gktalk.utils.common.*;
import com.rookie.gktalk.utils.exception.WebException;
import com.rookie.gktalk.utils.validate.DataAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Map;

/**
 * 用户Controller
 * @author Masker
 */
@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;

    /**
     * 注册用户
     * @param RequestBody 存储前端传过来的JSON数据
     * @param request
     * @return
     */
    @PostMapping("/register")
    public Object registerUser(@RequestBody Map<String,String> RequestBody,HttpServletRequest request) {
        //根据key来获取对应的值
        String userName = RequestBody.get("username");
        String password = RequestBody.get("password");
        String email = RequestBody.get("email");
//        String invitationCode = RequestBody.get("invitationCode");
        String verifyCode = RequestBody.get("verifyCode");

        //对获取的值进行校验
        DataAssert.notEmpty(userName,"请输入用户名!");
        DataAssert.notEmpty(password,"请输入密码！");
        DataAssert.notEmpty(email,"请输入注册邮箱！");
//        DataAssert.notEmpty(invitationCode,"请输入邀请码!");
        DataAssert.notEmpty(verifyCode,"请输入验证码！");

        //对获取的数据的格式进行校验
        DataAssert.isTrue(StringUtil.isValid(userName,StringUtil.USERNAME_REGEX),"请输入正确格式的用户名！");
        DataAssert.isTrue(StringUtil.isValid(password,StringUtil.PASSWORD_REGEX),"请输入正确的密码！");
        DataAssert.isTrue(StringUtil.isValid(email,StringUtil.EMAIL_REGEX),"请输入正确的邮件！");

        //对查询结果进行校验
        DataAssert.isNull(userService.selectUserByUserName(userName),"该用户名已被注册！");
        DataAssert.isNull(userService.selectUserByEmail(email),"该邮件已被注册！");

        HttpSession httpSession = request.getSession();
        String captcha = (String) httpSession.getAttribute("captcha");
        if(!verifyCode.equalsIgnoreCase(captcha)){
            throw new WebException("输入验证码错误！");
        }
//        if(!invitationCode.equalsIgnoreCase((String) httpSession.getAttribute("invitationCode"))){
//            throw new WebException("邀请码错误!");
//        }
//
//        String receiverMail = (String) httpSession.getAttribute("receiver");
//        if(!receiverMail.equals(email)){
//            throw new WebException("注册邮箱错误!");
//        }
//
//        Calendar deadTime = (Calendar) httpSession.getAttribute("deadTime");
//        Calendar currentTime = TimeUtil.getCurrentTime();
//        if(!TimeUtil.ifValid(currentTime,deadTime)){
//            throw new WebException("邀请码无效!");
//        }

        userService.addUser(userName,password,email);

        return "注册成功!";
    }

    /**
     * 设置头像
     * @param multipartFile 接受前端传输的文件
     * @param request
     * @return
     */
    //检验令牌
    @UserLoginToken
    @PostMapping("/avatar")
    public Object avatarUpload(@RequestParam("file")MultipartFile multipartFile,HttpServletRequest request){
        String username = request.getParameter("username");
        String filePath = FileUtil.fileUploadAvatar(multipartFile,request,username);

        //将用户头像文件存储路径保存至数据库
        userService.updateUser(username,null,null,filePath,null);

        return "头像设置完成!";
    }

    /**
     * 用户登录
     * @param request
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/login")
    public Object userLogin(@RequestBody Map<String,String> request,HttpServletRequest httpServletRequest){
        String username = request.get("username");
        String password = request.get("password");
        String verifyCode = request.get("verifyCode");

        HttpSession httpSession = httpServletRequest.getSession();
        String captcha = (String) httpSession.getAttribute("captcha");
        DataAssert.notEmpty(captcha,"未接收到验证码");
        if(!verifyCode.equalsIgnoreCase(captcha)){
            throw new WebException("输入验证码错误！");
        }

        User user = userService.selectUserByUserName(username);
        if (user == null){
            throw new WebException("未查询到该用户！");
        }

        String passwordFromDB = user.getPassword();
        //将用户传输至服务器的密码进行加密，与数据库存储的加密后的密码进行比对
        if(!new BCryptPasswordEncoder().matches(password,passwordFromDB)){
            throw new WebException("输入密码错误！");
        }

        String token = TokenUtil.getToken(user,"user",null);

        return new Result(200,"登陆成功",token);
    }

    /**
     * 修改用户密码
     * @param request
     * @return
     */
//    @UserLoginToken
    @PostMapping("/modifyUserPassword")
    public Object modifyUserInformation(@RequestBody Map<String,String> request){
        String username = request.get("username");
        String password = request.get("password");
        String rePassword = request.get("rePassword");

        if (!password.equals(rePassword)) {
            throw new WebException("新密码不一致！");
        }

        DataAssert.isTrue(StringUtil.isValid(password,StringUtil.PASSWORD_REGEX),"请输入正确的密码！");

        userService.updateUser(username,null,null,null,new BCryptPasswordEncoder().encode(password));

        return "修改成功";
    }

    /**
     * 重新设置密码的第一步
     * 利用邮箱发送验证码，验证用户身份
     * @param httpServletRequest
     * @param body
     * @return
     */
    @PostMapping("/verifyUser")
    public Object retrievePasswordFirstStep(HttpServletRequest httpServletRequest,
                                            @RequestBody Map<String,String> body){
        String captcha = body.get("captcha");
        String email = body.get("email");

        DataAssert.notEmpty(captcha,"请输入验证码！");
        DataAssert.notEmpty(email,"请输入注册的邮箱！");

        HttpSession httpSession = httpServletRequest.getSession();
        String receiverMail =(String) httpSession.getAttribute("receiverMail");
        String securityCode =(String) httpSession.getAttribute("securityCode");

        DataAssert.notNull(userService.selectUserByEmail(email),"该邮箱未注册用户！");

        if (!email.equals(receiverMail)){
            throw new WebException("邮箱错误！");
        }

        if (!captcha.equalsIgnoreCase(securityCode)){
            throw new WebException("验证码错误！");
        }

        Calendar deadTime = (Calendar) httpSession.getAttribute("deadTime");
        Calendar currentTime = TimeUtil.getCurrentTime();
        if(!TimeUtil.ifValid(currentTime,deadTime)){
            throw new WebException("安全码无效!");
        }

        return new Result(200,"身份验证成功",null);
    }

    /**
     * 重新设置密码的第二部
     * 修改用户密码（找回密码）
     * @param request
     * @param body
     * @return
     */
    @PostMapping("/retrievePassword")
    public Object retrievePasswordSecondStep(HttpServletRequest request,
                                             @RequestBody Map<String,String> body){
        String password = body.get("password");
        String repassword = body.get("repassword");

        if (!password.equals(repassword)){
            throw new WebException("密码不一致！");
        }

        HttpSession httpSession = request.getSession();
        String mail = (String) httpSession.getAttribute("receiverMail");
        User user = userService.selectUser(null,mail,null);
        userService.updateUser(user.getName(),null,null,null,password);

        return new Result(200,"找回密码成功",null);
    }
}
