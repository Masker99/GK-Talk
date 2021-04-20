package com.rookie.gktalk.mapper;

import com.rookie.gktalk.pojo.Article;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleMapper {
    int addOne(Article article);
    Article selectOneByArticleID(int artic_id);
}
