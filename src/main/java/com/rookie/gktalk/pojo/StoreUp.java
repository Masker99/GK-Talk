package com.rookie.gktalk.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class StoreUp {
    //文章id号
    private int art_id;
    //用户id号
    private int user_id;
    //收藏状态,1为收藏，0为取消收藏
    @Value("${some.key:1}")
    private int status;
}
