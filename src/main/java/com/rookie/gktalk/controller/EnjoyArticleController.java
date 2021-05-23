package com.rookie.gktalk.controller;

import com.rookie.gktalk.services.Impl.EnjoymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnjoyArticleController {
    @Autowired
    EnjoymentServiceImpl enjoymentService;

    @PostMapping
    public Object enjoy(){

        return null;
    }
}
