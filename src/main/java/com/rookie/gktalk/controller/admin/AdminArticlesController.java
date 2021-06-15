package com.rookie.gktalk.controller.admin;

import com.rookie.gktalk.dto.ArticleDto;
import com.rookie.gktalk.pojo.Article;
import com.rookie.gktalk.services.Impl.ArticleServiceImpl;
import com.rookie.gktalk.utils.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @PutMapping("/articles/approve/{article_id}")
    public Object approveArticle(@PathVariable("article_id")int article_id,
                                 HttpServletRequest request){
        Article article = articleService.searchArticle(article_id);
        article.setArtic_status(0);
        articleService.updateArticle(article);

        return new Result(200,"通过审核",null);
    }
}
