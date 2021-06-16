package com.rookie.gktalk.mapper;

import com.rookie.gktalk.pojo.Enjoyment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEnjoyMapper {
    int addOneEnjoyment(Enjoyment enjoymentOfUser);
    int cancelEnjoyment(int art_id,int user_id);
    int countNumForArticle(int articleId);
    Enjoyment selectOne(int articleID,int userID);
    List<Enjoyment> selectList(int userID);
}
