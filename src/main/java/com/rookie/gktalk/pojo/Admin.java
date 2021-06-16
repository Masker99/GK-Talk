package com.rookie.gktalk.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Admin {
    private int id;
    private String email;
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
}
