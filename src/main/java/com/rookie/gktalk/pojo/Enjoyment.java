package com.rookie.gktalk.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class Enjoyment {
    //用户id号
    private int user_id;
    //文章id
    private int article_id;
    //点赞状态，1为点赞，0为取消点赞
    @Value("#{1}")
    private int status;
}
