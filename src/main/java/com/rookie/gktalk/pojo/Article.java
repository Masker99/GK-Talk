package com.rookie.gktalk.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Article {
    private int artic_id;
    private String artic_title;
    private String artic_content;
    private int artic_author;

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private Date artic_date;

    @Value("#{0}")
    private int artic_status;//0为正常，1为注销，2为未审核
}
