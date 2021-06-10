package com.rookie.gktalk.dto;

import lombok.Data;

@Data
public class ArticleDto extends ContentDto{
    private int article_id;

    private String article_title;

    private AuthorDto author;

    private String article_content;

//    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss",timezone = "GMT+8")
//    private Date article_date;
}
