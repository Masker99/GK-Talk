package com.rookie.gktalk.services;

import com.rookie.gktalk.pojo.Enjoyment;

public interface EnjoymentService {
    int storeEnjoyment(Enjoyment enjoyment);
    int cancelEnjoyment(int userID,int articleID);
    int getNumberOfEnjoyment(int articleID);
}
