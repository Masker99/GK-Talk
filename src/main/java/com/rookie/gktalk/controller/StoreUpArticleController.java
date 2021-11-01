package com.rookie.gktalk.controller;

import com.rookie.gktalk.pojo.StoreUp;
import com.rookie.gktalk.pojo.User;
import com.rookie.gktalk.services.Impl.StoreUpServiceImpl;
import com.rookie.gktalk.services.Impl.UserServiceImpl;
import com.rookie.gktalk.utils.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/article")
public class StoreUpArticleController {
    @Autowired
    private StoreUpServiceImpl storeUpService;

    @Autowired
    private UserServiceImpl userService;

//    @UserLoginToken
    @PostMapping("/storeup/{articleid}")
    public Object storeUp(@PathVariable("articleid")int articleId,
                          HttpServletRequest request){
        String token = request.getHeader("Authorization");
        User user = userService.getUserFromToken(token);
        int userId = user.getUserID();

        StoreUp storeUp = new StoreUp(articleId,userId);
        storeUpService.storeUp(storeUp);

        return new Result(200,"收藏成功",null);
    }

//    @UserLoginToken
    @DeleteMapping("/storeup/{articleid}")
    public Object cancel(@PathVariable("articleid")int articleId,
                         HttpServletRequest request){
        String token = request.getHeader("Authorization");
        User user = userService.getUserFromToken(token);
        int userId = user.getUserID();

        storeUpService.cancelStoreUp(userId,articleId);

        return new Result(200,"取消收藏",null);
    }
}
