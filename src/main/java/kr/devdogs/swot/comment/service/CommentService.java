package kr.devdogs.swot.comment.service;

import kr.devdogs.swot.comment.dto.Comment;

import java.util.List;

public interface CommentService {
    public int create(Comment comment);
    // 특정 댓글 삭제
    public int deleteOne(int id, int userId);
    // 특정 게시글 댓글 전부 삭제
    public int deleteAll(int boardId);
    public List<Comment> findByBoardId(int boardId);
    public List<Comment> findByUserId(int userId);
}