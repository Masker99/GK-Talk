package com.rookie.gktalk.controller.admin;

import com.rookie.gktalk.dto.ArticleDto;
import com.rookie.gktalk.services.Impl.ArticleServiceImpl;
import com.rookie.gktalk.utils.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminArticlesController {
    @Autowired
    ArticleServiceImpl articleService;

    @GetMapping("/articles/unapproved")
    public Object getArticlesList(){
        List<ArticleDto> articleDtoList = articleService.getUnapprovedArticles();
        return new Result(200,"成功获取未审核的文章列表",articleDtoList);
    }
}
