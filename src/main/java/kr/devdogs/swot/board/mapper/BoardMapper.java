package kr.devdogs.swot.board.mapper;

import kr.devdogs.swot.board.dto.Board;

import java.util.List;

public interface BoardMapper {
    public int create(Board board);
    public Board findById(int id);
    public List<Board> findByUserId(int userId);
    public List<Board> findAll();
}
