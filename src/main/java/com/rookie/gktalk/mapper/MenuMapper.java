package com.rookie.gktalk.mapper;

import com.rookie.gktalk.pojo.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {
    List<Menu> findAll();
}
