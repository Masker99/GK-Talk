package com.rookie.gktalk.controller;

import com.rookie.gktalk.pojo.Article;
import com.rookie.gktalk.pojo.User;
import com.rookie.gktalk.services.Impl.ArticleServiceImpl;
import com.rookie.gktalk.services.Impl.UserServiceImpl;
import com.rookie.gktalk.utils.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class ArticleController {
    @Autowired
    ArticleServiceImpl articleService;
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/article")
    public Object postAnArticle(@RequestParam("article_title") String articleTitle,
                                @RequestParam("article_content") String articleContent,
                                HttpServletRequest request){
        String token = request.getHeader("token");

        User user = userService.getUserFromToken(token);

        Article article = new Article();
        article.setArtic_title(articleTitle);
        article.setArtic_author(user.getUserID());
        article.setArtic_content(articleContent);
        article.setArtic_date(new Date());

        articleService.storeArticle(article);

        return "成功发表文章!";
    }

    @GetMapping("/articles")
    public Object getArticleList(){
        return new Result(200,"成功获取文章列表",articleService.getAllArticles());
    }
}
