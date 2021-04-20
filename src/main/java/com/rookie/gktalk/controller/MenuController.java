package com.rookie.gktalk.controller;

import com.rookie.gktalk.pojo.Menu;
import com.rookie.gktalk.services.Impl.MenuServiceImpl;
import com.rookie.gktalk.utils.common.Result;
import com.rookie.gktalk.utils.validate.DataAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {
    @Autowired
    MenuServiceImpl menuService;

    @GetMapping("/menu")
    public Object getMenuList(){
        List<Menu> menuList = menuService.MenuList();
        DataAssert.notNull(menuList,"菜单获取失败！");

        return new Result(200,"菜单",menuList);
    }
}
