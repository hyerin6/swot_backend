package kr.devdogs.swot.board.mapper;

import kr.devdogs.swot.board.dto.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    public int create(Board board);
    public Board findById(int id);
    public List<Board> findByUserId(int userId);
    public List<Board> findAll();
}
