package com.rookie.gktalk.pojo;

import lombok.Data;

@Data
public class Menu {
    //菜单选项名称
    private String name;

    //菜单选项id
    private int menu_id;

    //父级id
    private int parent_id;

    //菜单对应的url
    private String url;

    //优先级
    private int priority;
}
