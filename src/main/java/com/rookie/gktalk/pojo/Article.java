package com.rookie.gktalk.pojo;

import lombok.Data;
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
}
