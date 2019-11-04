package kr.devdogs.swot.comment.service;

import kr.devdogs.swot.comment.dto.Comment;
import kr.devdogs.swot.comment.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("comentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public int create(Comment comment){
        return commentMapper.create(comment);
    }

    // 특정 댓글 삭제
    @Override
    public int deleteOne(int id, int userId){
        return commentMapper.deleteOne(id, userId);
    }

    // 특정 게시글 댓글 전부 삭제
    @Override
    public int deleteAll(int boardId){
        return commentMapper.deleteAll(boardId);
    }

    @Override
    public List<Comment> findByBoardId(int boardId){
        return commentMapper.findByBoardId(boardId);
    }

    @Override
    public List<Comment> findByUserId(int userId){
        return commentMapper.findByUserId(userId);
    }
}
