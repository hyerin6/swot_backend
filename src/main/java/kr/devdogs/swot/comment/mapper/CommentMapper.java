package kr.devdogs.swot.comment.mapper;

import kr.devdogs.swot.comment.dto.Comment;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CommentMapper {
    public int create(Comment comment);
    // 특정 댓글 삭제
    public int deleteOne(int id, int userId);
    // 특정 게시글 댓글 전부 삭제
    public int deleteAll(int boardId);
    public List<Comment> findByBoardId(int boardId);
    public List<Comment> findByUserId(int userId);
    public int deleteByUserId(int userId);
}
