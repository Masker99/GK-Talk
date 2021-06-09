package com.rookie.gktalk.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DynamicDto {
    private int dynamic_id;
    private String content;
    private Date date;
    private AuthorDto author;
}
