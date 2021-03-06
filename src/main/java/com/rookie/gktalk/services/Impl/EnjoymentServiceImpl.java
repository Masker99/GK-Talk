package com.rookie.gktalk.services.Impl;

import com.rookie.gktalk.mapper.UserEnjoyMapper;
import com.rookie.gktalk.pojo.Enjoyment;
import com.rookie.gktalk.services.EnjoymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnjoymentServiceImpl implements EnjoymentService {
    @Autowired
    private UserEnjoyMapper userEnjoyMapper;

    @Override
    public int storeEnjoyment(Enjoyment enjoyment){
        return userEnjoyMapper.addOneEnjoyment(enjoyment);
    }

    @Override
    public int cancelEnjoyment(int userID,int articleID){
        return userEnjoyMapper.cancelEnjoyment(articleID,userID);
    }

    @Override
    public int getNumberOfEnjoyment(int articleID){
        return userEnjoyMapper.countNumForArticle(articleID);
    }

    @Override
    public Enjoyment ifEnjoyment(int articleID,int userID){
        return userEnjoyMapper.selectOne(articleID,userID);
    }

    @Override
    public List<Enjoyment> getListOfEnjoymentByUserId(int userID){
        return userEnjoyMapper.selectList(userID);
    }
}
