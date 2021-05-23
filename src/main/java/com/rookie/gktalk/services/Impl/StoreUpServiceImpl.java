package com.rookie.gktalk.services.Impl;

import com.rookie.gktalk.mapper.StoreUpMapper;
import com.rookie.gktalk.pojo.StoreUp;
import com.rookie.gktalk.services.StoreUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreUpServiceImpl implements StoreUpService {
    @Autowired
    StoreUpMapper storeUpMapper;

    @Override
    public int storeUp(StoreUp storeUp){
        return storeUpMapper.addOne(storeUp);
    }

    @Override
    public int cancelStoreUp(int userID,int articleID){
        return storeUpMapper.cancel(articleID,userID);
    }

    @Override
    public int getNumberOfStores(int articleID){
        return storeUpMapper.countNum(articleID);
    }
}
