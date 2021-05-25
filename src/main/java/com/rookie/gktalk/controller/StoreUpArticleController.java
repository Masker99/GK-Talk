package com.rookie.gktalk.controller;

import com.rookie.gktalk.pojo.StoreUp;
import com.rookie.gktalk.services.Impl.StoreUpServiceImpl;
import com.rookie.gktalk.utils.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class StoreUpArticleController {
    @Autowired
    private StoreUpServiceImpl storeUpService;

    @PostMapping("/storeup/{articleid}/{userid")
    public Object storeUp(@PathVariable("articleid")int articleId,
                          @PathVariable("userid")int userId){
        StoreUp storeUp = new StoreUp(articleId,userId);
        storeUpService.storeUp(storeUp);

        return new Result(200,"收藏成功",null);
    }

    @DeleteMapping("/storeup/{articleid}/{userid")
    public Object cancel(@PathVariable("articleid")int articleId,
                         @PathVariable("userid")int userId){
        storeUpService.cancelStoreUp(userId,articleId);

        return new Result(200,"取消收藏",null);
    }
}
