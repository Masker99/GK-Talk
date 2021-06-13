package com.rookie.gktalk.services.Impl;

import com.rookie.gktalk.dto.CommentDto;
import com.rookie.gktalk.mapper.CommentMapper;
import com.rookie.gktalk.pojo.Comment;
import com.rookie.gktalk.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public int addOneComment(Comment comment) {
        return commentMapper.addOneComment(comment);
    }

    @Override
    public int deleteOneComment(int commentID) {
        return commentMapper.updateOneComment(commentID);
    }

    @Override
    public Comment getOneByCommentId(int commentID){
        return commentMapper.selectOneCommentByCommentId(commentID);
    }

    @Override
    public List<CommentDto> getListCommentByContentID(int contentID) {
        return commentMapper.getListByContentId(contentID);
    }
}
