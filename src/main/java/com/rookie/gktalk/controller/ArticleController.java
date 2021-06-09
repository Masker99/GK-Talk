package com.rookie.gktalk.controller;

import com.rookie.gktalk.dto.ArticleDto;
import com.rookie.gktalk.pojo.Article;
import com.rookie.gktalk.pojo.User;
import com.rookie.gktalk.services.Impl.ArticleServiceImpl;
import com.rookie.gktalk.services.Impl.UserServiceImpl;
import com.rookie.gktalk.utils.annotation.UserLoginToken;
import com.rookie.gktalk.utils.common.Result;
import com.rookie.gktalk.utils.validate.DataAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@RestController
public class ArticleController {
    @Autowired
    ArticleServiceImpl articleService;
    @Autowired
    UserServiceImpl userService;

    /**
     * 发表文章
     * @param body
     * @param request
     * @return
     */
    @UserLoginToken
    @PostMapping("/article")
    public Object postAnArticle(@RequestBody Map<String,String> body,
                                HttpServletRequest request){
        String token = request.getHeader("Authorization");
        DataAssert.notEmpty(token,"获取token失败");
        User user = userService.getUserFromToken(token);

        String articleTitle = body.get("article_title");
        String articleContent = body.get("article_content");

        Article article = new Article();
        article.setArtic_title(articleTitle);
        article.setArtic_author(user.getUserID());
        article.setArtic_content(articleContent);
        article.setArtic_date(new Date());

        articleService.storeArticle(article);

        return new Result(200,"成功发表文章!",null);
    }

    /**
     * 获取文章列表
     * @return
     */
    @GetMapping("/articles")
    public Object getArticleList(){
        return new Result(200,"成功获取文章列表",articleService.getAllArticles());
    }

    @UserLoginToken
    @PutMapping("/article/{article_id}")
    public Object updateArticle(@RequestBody Map<String,String> body,
                                @PathVariable("article_id") int artic_id){
        Article usedArticle = articleService.searchArticle(artic_id);

        String title = body.get("article_title");
        String content = body.get("article_content");

        Article newArticle = new Article();
        newArticle.setArtic_id(artic_id);
        if(!title.equals(usedArticle.getArtic_title())){
            newArticle.setArtic_title(title);
            newArticle.setArtic_date(new Date());
        }
        if (!content.equals(usedArticle.getArtic_content())){
            newArticle.setArtic_content(content);
            newArticle.setArtic_date(new Date());
        }

        articleService.updateArticle(newArticle);

        return new Result(200,"文章成功更新",null);
    }

    @UserLoginToken
    @DeleteMapping("/article/{article_id}")
    public Object deleteArticle(@PathVariable("article_id") int artic_id){
        articleService.deleteArticle(artic_id);

        return new Result(200,"成功删除文章",null);
    }

    @GetMapping("/article/{article_id}")
    public Object getArticle(@PathVariable("article_id") int artic_id){
       ArticleDto article =  articleService.getOneArticle(artic_id);

       return new Result(200,"成功获取文章",article);
    }
}
