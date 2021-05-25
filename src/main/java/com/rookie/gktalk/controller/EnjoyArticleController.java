package com.rookie.gktalk.controller;

import com.rookie.gktalk.pojo.Enjoyment;
import com.rookie.gktalk.services.Impl.EnjoymentServiceImpl;
import com.rookie.gktalk.utils.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class EnjoyArticleController {
    @Autowired
    EnjoymentServiceImpl enjoymentService;

    @PostMapping("/enjoyment/{articleid}/{userid}")
    public Object enjoy(@PathVariable("articleid")int articleId,
                        @PathVariable("userid")int userId){
        Enjoyment enjoyment = new Enjoyment(articleId, userId);
        enjoymentService.storeEnjoyment(enjoyment);

        return new Result(200,"点赞成功",null);
    }

    @DeleteMapping("/enjoyment/{articleid}/{userid}")
    public Object cancel(@PathVariable("articleid")int articleId,
                         @PathVariable("userid")int userId){
        enjoymentService.cancelEnjoyment(userId,articleId);

        return new Result(200,"取消点赞",null);
    }
}
