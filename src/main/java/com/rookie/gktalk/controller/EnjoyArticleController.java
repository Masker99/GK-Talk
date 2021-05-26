package com.rookie.gktalk.controller;

import com.rookie.gktalk.pojo.Enjoyment;
import com.rookie.gktalk.pojo.User;
import com.rookie.gktalk.services.Impl.EnjoymentServiceImpl;
import com.rookie.gktalk.services.Impl.UserServiceImpl;
import com.rookie.gktalk.utils.annotation.UserLoginToken;
import com.rookie.gktalk.utils.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/article")
public class EnjoyArticleController {
    @Autowired
    EnjoymentServiceImpl enjoymentService;

    @Autowired
    UserServiceImpl userService;
    @UserLoginToken
    @PostMapping("/enjoyment/{articleid}")
    public Object enjoy(@PathVariable("articleid")int articleId,
                        HttpServletRequest request){
        String token = request.getHeader("token");
        User user = userService.getUserFromToken(token);
        int userId = user.getUserID();

        Enjoyment enjoyment = new Enjoyment(articleId, userId);
        enjoymentService.storeEnjoyment(enjoyment);

        return new Result(200,"点赞成功",null);
    }

    @DeleteMapping("/enjoyment/{articleid}")
    public Object cancel(@PathVariable("articleid")int articleId,
                         HttpServletRequest request){
        String token = request.getHeader("token");
        User user = userService.getUserFromToken(token);
        int userId = user.getUserID();

        enjoymentService.cancelEnjoyment(userId,articleId);

        return new Result(200,"取消点赞",null);
    }
}
