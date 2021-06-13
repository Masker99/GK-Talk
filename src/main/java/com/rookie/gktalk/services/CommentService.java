package com.rookie.gktalk.services;

import com.rookie.gktalk.dto.CommentDto;
import com.rookie.gktalk.pojo.Comment;

import java.util.List;

public interface CommentService {
    int addOneComment(Comment comment);
    int deleteOneComment(int commentID);
    Comment getOneByCommentId(int commentID);
    List<CommentDto> getListCommentByContentID(int contentID);
}
