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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/article")
public class EnjoyArticleController {
    @Autowired
    EnjoymentServiceImpl enjoymentService;

    @Autowired
    UserServiceImpl userService;

    @UserLoginToken
    @PostMapping("/enjoyment/{articleId}")
    public Object enjoy(@PathVariable("articleId")int articleId,
                        HttpServletRequest request){
        String token = request.getHeader("Authorization");
        User user = userService.getUserFromToken(token);
        int userId = user.getUserID();

        Enjoyment enjoyment = new Enjoyment(articleId, userId);
        enjoymentService.storeEnjoyment(enjoyment);

        return new Result(200,"点赞成功",null);
    }

    @UserLoginToken
    @DeleteMapping("/enjoyment/{articleId}")
    public Object cancel(@PathVariable("articleId")int articleId,
                         HttpServletRequest request){
        String token = request.getHeader("Authorization");
        User user = userService.getUserFromToken(token);
        int userId = user.getUserID();

        enjoymentService.cancelEnjoyment(userId,articleId);

        return new Result(200,"取消点赞",null);
    }

    @UserLoginToken
    @GetMapping("/enjoyment/{articleId}")
    public Object ifLiked(@PathVariable("articleId") int articleId,
                          HttpServletRequest request){
        String token = request.getHeader("Authorization");
        User user = userService.getUserFromToken(token);
        int userId = user.getUserID();

        Enjoyment enjoyment = enjoymentService.ifEnjoyment(articleId,userId);
        boolean ifLiked = false;
        if(enjoyment != null){
            if(enjoyment.getStatus() == 1){
                ifLiked = true;
            }
        }
        return new Result(200,"成功查询结果",ifLiked);
    }

    @GetMapping("/enjoyment/list")
    public Object getListOfEnjoyment(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        User user = userService.getUserFromToken(token);
        int userId = user.getUserID();

        List<Enjoyment> enjoymentList = enjoymentService.getListOfEnjoymentByUserId(userId);
        List<Integer> resultList = new ArrayList<>();
        for (Enjoyment e: enjoymentList) {
            resultList.add(e.getArticle_id());
        }

        return new Result(200,"成功获取点赞列表",resultList);
    }
}
