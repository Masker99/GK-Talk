package com.rookie.gktalk.services;

import com.rookie.gktalk.pojo.StoreUp;

public interface StoreUpService {
    int storeUp(StoreUp storeUp);
    int cancelStoreUp(int userID,int articleID);
    int getNumberOfStores(int articleID);
}
