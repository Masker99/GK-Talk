package com.rookie.gktalk.dto;

import lombok.Data;

@Data
public class DynamicDto extends ContentDto{
    private int dynamic_id;
    private String content;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
//    private Date date;
    private AuthorDto author;
}
