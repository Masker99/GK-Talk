package com.rookie.gktalk.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    //用户ID
    private Integer userID;

    //用户名
    private String name;

    //性别
    private String sex;

    //绑定邮箱
    private String email;

    //登陆密码
    private String password;

    //用户状态
    @Value("#{0}")
    private String status;//0 正常,1 无效

    //注册时间
    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private Date register_time;

    //最后一次修改时间
    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private Date modify_time;

    //用户头像的图片路径
    private String picpath;

    //积分
    @Value("#{0}")
    private Integer score;
}
