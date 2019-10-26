package kr.devdogs.swot.board.service;

import kr.devdogs.swot.board.dto.Board;

import java.util.List;

public interface BoardService {
    public int create(Board board);
    public Board findById(int id);
    public List<Board> findByUserId(int userId);
    public List<Board> findAll();
}
