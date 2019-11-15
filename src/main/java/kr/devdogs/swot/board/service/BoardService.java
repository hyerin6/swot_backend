package kr.devdogs.swot.board.service;

import kr.devdogs.swot.board.dto.Board;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BoardService {
    public int create(Board board);
    public Board findById(int code, int id);
    public Board find(int id);
    public List<Board> findByUserId(int userId);
    public List<Board> findAll(int code);
    public Board modify(Board board);
    public int delete(int id);
    public int complete(int id);
    public int deleteByUserId(int userId);
    public Board findByMyStudy(int id);
}
