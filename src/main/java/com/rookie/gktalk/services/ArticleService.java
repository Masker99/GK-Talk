package com.rookie.gktalk.services;

import com.rookie.gktalk.dto.ArticleDto;
import com.rookie.gktalk.dto.AuthorDto;
import com.rookie.gktalk.pojo.Article;

import java.util.List;

public interface ArticleService {
    int storeArticle(Article article);
    Article searchArticle(int artic_id);
    List<ArticleDto> getAllArticles();
    int deleteArticle(int artic_id);
    int updateArticle(Article article);
}
