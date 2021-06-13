package com.rookie.gktalk.mapper;

import com.rookie.gktalk.dto.CommentDto;
import com.rookie.gktalk.pojo.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
    int addOneComment(Comment comment);
    int updateOneComment(int commentID);
    Comment selectOneCommentByCommentId(int commentID);
    List<CommentDto> getListByContentId(int contentID);
}
