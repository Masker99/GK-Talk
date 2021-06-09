package com.rookie.gktalk.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Dynamic {
    private int dynamic_id;
    private int author_id;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date date;
    @Value("#{0}")
    private int status;//动态状态标志，0为正常，1为删除
}
