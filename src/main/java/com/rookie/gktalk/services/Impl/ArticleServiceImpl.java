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

    public int storeArticle(Article article){
        int result = articleMapper.addOne(article);

        return result;
    }

    public List<ArticleDto> getAllArticles(){
        return articleMapper.selectAll();
    }

}
