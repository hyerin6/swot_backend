package kr.devdogs.swot.board.service;

import kr.devdogs.swot.application.dto.Application;
import kr.devdogs.swot.application.mapper.ApplicationMapper;
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
    @Autowired ApplicationMapper applicationMapper;

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

    /*
    스터디
    1. 게시글 작성자가 스터디 모집을 하지 않고 게시글을 삭제하고 싶을 때 - 모든 예약이 삭제(거절) 처리
    2. 게시글 작성자가 신청 완료 버튼을 눌렀을 때 승인 처리했던 사람들은 그대로 예약 승인 처리, 대기 상태이거나
    승인 거절된 사람들은 예약 거절 처리, 신청 완료 버튼을 누르면 게시글은 삭제할 수 없음.
    3. 스터디를 신청한 사용자가 대기 상태, 승인 상태이고 모집이 끝나지 않았으면 취소가 가능하지만,
    게시글 작성자가 스터디 모집 완료 버튼을 눌러 예약 완료 상태가 되었으면
    스터디 예약을 취소 할 수 없다.

    대기상태 'C'
    승인상태 'T'
    취소상태 'R'
    삭제 및 거절 상태 'D'
    스터디 모집 완료 상태 'S' -> 예약 뿐만 아니라 게시글의 상태도 'S'로 변경된다.
     */
    // 스터디 모집 게시글 삭제
    @Override
    public int delete(int id){
        char state = boardMapper.find(id).getState();
        if(state == 'S'){ // 모집 완료된 경우, 삭제할 수 없음.
            return 0;
        }
        int result = boardMapper.delete(id);
        result += commentMapper.deleteAll(id);
        List<Application> applications = applicationMapper.findByBoardId(id);
        for(Application a : applications){
            result += applicationMapper.decline(a.getId()); // 전부 거절 ('D') 처리
        }
        return result;
    }

    // 스터디 모집 완료
    @Override
    public int complete(int id){
        List<Application> applications = applicationMapper.findByBoardId(id);
        for(Application a : applications){
            if(a.getState() == 'T'){ // 승인된 예약은 S로 변경
                applicationMapper.complete(a.getId()); // 'T' -> 'S'
            } else {
                applicationMapper.decline(a.getId()); // 나머지는 전부 'D' 로 변경
            }
        }
        return boardMapper.complete(id);
    }


    // 회원 탈퇴의 경우
    @Override
    public int deleteByUserId(int userId){
        List<Board> boards = boardMapper.findByUserId(userId);
        for(Board b : boards) {
            applicationMapper.deleteByBoardId(b.getId());
        }
        return boardMapper.deleteByUserId(userId);
    }

    @Override
    public Board findByMyStudy(int id){
        return boardMapper.findByMyStudy(id);
    }

}
