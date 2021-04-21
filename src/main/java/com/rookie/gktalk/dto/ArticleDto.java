package com.rookie.gktalk.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleDto {
    private int article_id;
    private String article_title;
    private AuthorDto article_author;
    private String article_content;
    private Date article_date;
}
