package com.rookie.gktalk.controller;

import com.rookie.gktalk.dto.CommentDto;
import com.rookie.gktalk.pojo.Comment;
import com.rookie.gktalk.pojo.User;
import com.rookie.gktalk.services.Impl.CommentServiceImpl;
import com.rookie.gktalk.services.Impl.UserServiceImpl;
import com.rookie.gktalk.utils.annotation.UserLoginToken;
import com.rookie.gktalk.utils.common.Result;
import com.rookie.gktalk.utils.exception.WebException;
import com.rookie.gktalk.utils.validate.DataAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentServiceImpl commentService;
    @Autowired
    UserServiceImpl userService;

//    @UserLoginToken
    @PostMapping("/{contentID}")
    public Object postComment(@PathVariable("contentID") int content_id,
                              @RequestBody Map<String,String> body,
                              HttpServletRequest request){
        String token = request.getHeader("Authorization");
        DataAssert.notEmpty(token,"未能成功获取token");
        User user =  userService.getUserFromToken(token);

        String content = body.get("content");

        Comment comment = new Comment();
        comment.setContent_id(content_id);
        comment.setContent(content);
        comment.setDate(new Date());
        comment.setUser_id(user.getUserID());

        commentService.addOneComment(comment);

        return new Result(200,"成功发布评论",null);
    }

    @GetMapping("/{contentID}")
    public Object getCommentList(@PathVariable("contentID") int content_id){
        List<CommentDto> commentDtoList = commentService.getListCommentByContentID(content_id);

        return new Result(200,"成功获取评论列表",commentDtoList);
    }

    @UserLoginToken
    @DeleteMapping("/{commentID}")
    public Object deleteComment(@PathVariable("commentID")int comment_id,
                                HttpServletRequest request){
        String token = request.getHeader("Authorization");
        DataAssert.notEmpty(token,"未能获取token");
        User user = userService.getUserFromToken(token);
        Comment comment = commentService.getOneByCommentId(comment_id);
        if(!user.getUserID().equals(comment.getUser_id())){
            throw new WebException("您不能删除该评论！");
        }

        commentService.deleteOneComment(comment_id);

        return new Result(200,"成功删除评论",null);
    }
}
