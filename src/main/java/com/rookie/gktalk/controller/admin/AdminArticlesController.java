package com.rookie.gktalk.controller.admin;

import com.rookie.gktalk.dto.ArticleDto;
import com.rookie.gktalk.services.Impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminArticlesController {
    @Autowired
    ArticleServiceImpl articleService;

    public Object getArticlesList(){
        List<ArticleDto> articleDtoList = articleService.getUnapprovedArticles();
        return null;
    }
}
