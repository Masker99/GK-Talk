package com.rookie.gktalk.services;

import com.rookie.gktalk.pojo.Enjoyment;

import java.util.List;

public interface EnjoymentService {
    int storeEnjoyment(Enjoyment enjoyment);
    int cancelEnjoyment(int userID,int articleID);
    int getNumberOfEnjoyment(int articleID);
    Enjoyment ifEnjoyment(int articleID,int userID);
    List<Enjoyment> getListOfEnjoymentByUserId(int userID);
}
