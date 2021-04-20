package com.rookie.gktalk.services.Impl;

import com.rookie.gktalk.mapper.ArticleMapper;
import com.rookie.gktalk.pojo.Article;
import com.rookie.gktalk.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    public Article storeArticle(Article article){
        articleMapper.addOne(article);

        return articleMapper.selectOneByArticleID(article.getArtic_id());
    }
}
