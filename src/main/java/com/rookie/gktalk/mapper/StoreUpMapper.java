package com.rookie.gktalk.mapper;

import com.rookie.gktalk.pojo.StoreUp;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreUpMapper {
    int addOne(StoreUp storeUp);
    int cancel(int art_id,int user_id);
    int countNum(int art_Id);
}
