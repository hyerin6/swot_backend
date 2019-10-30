package kr.devdogs.swot.board.service;

import kr.devdogs.swot.board.dto.Board;
import kr.devdogs.swot.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BoardService")
public class BoardServiceImpl implements BoardService{

    @Autowired
    BoardMapper boardMapper;

    @Override
    public int create(Board board){
        return boardMapper.create(board);
    }

    @Override
    public Board modify(Board board){
        return null;
    }

    @Override
    public Board findById(int id){
        return boardMapper.findById(id);
    }

    @Override
    public List<Board> findByUserId(int userId){
        return boardMapper.findByUserId(userId);
    }

    @Override
    public List<Board> findAll(){
        return boardMapper.findAll();
    }

    @Override
    public int delete(int id){
        return 1;
    }

}
