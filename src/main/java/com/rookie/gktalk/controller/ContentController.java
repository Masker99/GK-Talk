package com.rookie.gktalk.controller;

import com.rookie.gktalk.dto.ArticleDto;
import com.rookie.gktalk.dto.ContentDto;
import com.rookie.gktalk.dto.DynamicDto;
import com.rookie.gktalk.services.Impl.ArticleServiceImpl;
import com.rookie.gktalk.services.Impl.DynamicServiceImpl;
import com.rookie.gktalk.utils.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class ContentController {
    @Autowired
    DynamicServiceImpl dynamicService;
    @Autowired
    ArticleServiceImpl articleService;

    @GetMapping("/content")
    public Object getAllContent(){
        List<DynamicDto> dynamicDtoList = dynamicService.getListOfDynamic();
        List<ArticleDto> articleDtoList = articleService.getAllArticles();

        List<ContentDto> contentList = new ArrayList<>();
        contentList.addAll(dynamicDtoList);
        contentList.addAll(articleDtoList);

        Collections.sort(contentList);

        return new Result(200,"成功获取内容",contentList);
    }
}
