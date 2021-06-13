package com.rookie.gktalk.controller;

import com.github.pagehelper.PageHelper;
import com.rookie.gktalk.dto.DynamicDto;
import com.rookie.gktalk.pojo.Dynamic;
import com.rookie.gktalk.pojo.User;
import com.rookie.gktalk.services.Impl.DynamicServiceImpl;
import com.rookie.gktalk.services.Impl.UserServiceImpl;
import com.rookie.gktalk.utils.annotation.UserLoginToken;
import com.rookie.gktalk.utils.common.Result;
import com.rookie.gktalk.utils.validate.DataAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dynamic")
public class DynamicController {
    @Autowired
    DynamicServiceImpl dynamicService;
    @Autowired
    UserServiceImpl userService;

    @UserLoginToken
    @PostMapping("/")
    public Object postDynamic(@RequestBody Map<String,String> body,
                              HttpServletRequest request){
        String token = request.getHeader("Authorization");
        DataAssert.notNull(token,"获取token失败");
        User user =  userService.getUserFromToken(token);
        int author_id = user.getUserID();

        Dynamic dynamic = new Dynamic();
        dynamic.setAuthor_id(author_id);
        String content = body.get("content");
        dynamic.setContent(content);
        dynamic.setDate(new Date());

        dynamicService.addOneDynamic(dynamic);

        return new Result(200,"成功发布用户动态",null);
    }

    @DeleteMapping("/{dynamic_id}")
    public Object deleteDynamic(@PathVariable("dynamic_id") int dynamic_id){
        dynamicService.deleteOneDynamic(dynamic_id);

        return new Result(200,"成功删除用户动态",null);
    }

    @GetMapping("/all")
    public Object getListDynamic(){
        PageHelper.startPage(1,3);
        List<DynamicDto> dynamicList = dynamicService.getListOfDynamic();

        return new Result(200,"成功获取动态",dynamicList);
    }

    @PutMapping("/{dynamic_id}")
    public Object updateDynamic(@PathVariable("dynamic_id")int dynamic_id,
                                @RequestBody Map<String,String> body){
        String content = body.get("content");
        //未完成

        return null;
    }
}
