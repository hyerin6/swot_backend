package kr.devdogs.swot.board.service;

import kr.devdogs.swot.board.dto.Board;
import kr.devdogs.swot.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BoardService")
public class BoardServiceImpl {

    @Autowired
    BoardMapper boardMapper;

    public int create(Board board){
        return boardMapper.create(board);
    }

    public Board findById(int id){
        return boardMapper.findById(id);
    }

    public List<Board> findByUserId(int userId){
        return boardMapper.findByUserId(userId);
    }

    public List<Board> findAll(){
        return boardMapper.findAll();
    }
}
