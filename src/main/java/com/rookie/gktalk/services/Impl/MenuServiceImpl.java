package com.rookie.gktalk.services.Impl;

import com.rookie.gktalk.mapper.MenuMapper;
import com.rookie.gktalk.pojo.Menu;
import com.rookie.gktalk.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;
    @Override
    public List<Menu> MenuList() {
        return menuMapper.findAll();
    }
}
