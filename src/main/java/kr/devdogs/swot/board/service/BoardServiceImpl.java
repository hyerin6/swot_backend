package kr.devdogs.swot.board.service;

import kr.devdogs.swot.board.dto.Board;
import kr.devdogs.swot.board.mapper.BoardMapper;
import kr.devdogs.swot.comment.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BoardService")
public class BoardServiceImpl implements BoardService{

    @Autowired BoardMapper boardMapper;
    @Autowired CommentMapper commentMapper;

    @Override
    public int create(Board board){
        boardMapper.create(board);
        return board.getId();
    }

    @Override
    public Board modify(Board board) {
        int insertLine = boardMapper.modify(board);
        if(insertLine == 1) return find(board.getId());
        else return null;
    }

    @Override
    public Board find(int id){
        return boardMapper.find(id);
    }

    @Override
    public Board findById(int code, int id){
        return boardMapper.findById(code, id);
    }

    @Override
    public List<Board> findByUserId(int userId){
        return boardMapper.findByUserId(userId);
    }

    @Override
    public List<Board> findAll(int code){
        return boardMapper.findAll(code);
    }

    @Override
    public int delete(int id){
        int result = boardMapper.delete(id);
        result += commentMapper.deleteAll(id);
        return result;
    }

}
