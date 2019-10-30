package kr.devdogs.swot.board.service;

import kr.devdogs.swot.board.dto.Board;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BoardService {
    public int create(Board board);
    public Board findById(int id);
    public List<Board> findByUserId(int userId);
    public List<Board> findAll();
    public Board modify(Board board);
    public int delete(int id);
}
