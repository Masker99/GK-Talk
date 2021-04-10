package com.rookie.gktalk.controller;

import com.rookie.gktalk.services.UserService;
import com.rookie.gktalk.utils.common.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class FileController {
    @Autowired
    UserService userService;

    @GetMapping("/getAvatar")
    public Object getAvatar(@RequestBody Map<String,String> request,HttpServletResponse response){
        String username = request.get("username");
        String pitcherPath = userService.getUserAvatar(username);

        FileUtil.outFile(pitcherPath,response);

        return "获得头像";
    }
}
