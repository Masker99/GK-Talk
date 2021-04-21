package com.rookie.gktalk.services.Impl;

import com.rookie.gktalk.dto.ArticleDto;
import com.rookie.gktalk.mapper.ArticleMapper;
import com.rookie.gktalk.mapper.UserMapper;
import com.rookie.gktalk.pojo.Article;
import com.rookie.gktalk.services.ArticleService;
import com.rookie.gktalk.utils.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    UserServiceImpl userService;

    public Article storeArticle(Article article){
        articleMapper.addOne(article);

        return articleMapper.selectOneByArticleID(article.getArtic_id());
    }

    public List<ArticleDto> getAllArticles(){
        List<Article> articleList = articleMapper.selectAll();
        List<ArticleDto> articleDtoList = new ArrayList<>();
        for (Article article : articleList) {
            ArticleDto articleDto = new ArticleDto();
            articleDto.setArticle_id(article.getArtic_id());
            articleDto.setArticle_title(article.getArtic_title());
            articleDto.setArticle_author(userService.getAuthorDto(String.valueOf(article.getArtic_author())));
            articleDto.setArticle_date(article.getArtic_date());
            articleDtoList.add(articleDto);
        }

        return articleDtoList;
    }

}
