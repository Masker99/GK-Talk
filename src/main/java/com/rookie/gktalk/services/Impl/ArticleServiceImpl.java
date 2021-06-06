package com.rookie.gktalk.services.Impl;

import com.rookie.gktalk.dto.ArticleDto;
import com.rookie.gktalk.mapper.ArticleMapper;
import com.rookie.gktalk.pojo.Article;
import com.rookie.gktalk.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    UserServiceImpl userService;

    public int storeArticle(Article article){
        int result = articleMapper.addOne(article);

        return result;
    }

    public Article searchArticle(int artic_id){
        return articleMapper.selectOneByArticleID(artic_id);
    }

    public List<ArticleDto> getAllArticles(){
        return articleMapper.selectAll();
    }

    public int deleteArticle(int artic_id){
        Article article = new Article();
        article.setArtic_id(artic_id);

        article.setArtic_status(1);

        int success = articleMapper.updateOne(article);
        return success;
    }

    public int updateArticle(Article article){
        return articleMapper.updateOne(article);
    }

    @Override
    public List<ArticleDto> getUnapprovedArticles() {
        return articleMapper.selectUnapprovedArticles();
    }
}
