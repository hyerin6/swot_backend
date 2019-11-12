package kr.devdogs.swot.application.service;

import kr.devdogs.swot.application.dto.Application;
import kr.devdogs.swot.application.mapper.ApplicationMapper;
import kr.devdogs.swot.board.dto.Board;
import kr.devdogs.swot.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired ApplicationMapper applicationMapper;
    @Autowired BoardMapper boardMapper;

    public int create(Application application){
        return applicationMapper.create(application);
    }

    public int accept(int id){
        return applicationMapper.accept(id);
    }

    public int decline(int id){
        return applicationMapper.decline(id);
    }

    public List<Application> findByBoardId(int boardId){
        return applicationMapper.findByBoardId(boardId);
    }

    public Application findById(int id){
        return applicationMapper.findById(id);
    }

    public List<Application> myAcceptStudy(int userId){
        return applicationMapper.myAcceptStudy(userId);
    }

    public List<Application> findByUserId(int userId){
        List<Application> list = applicationMapper.findByUserId(userId);
        if(list.isEmpty()){
            return null;
        }
        return list;
    }

    public int delete(int id){ // 스터디 예약 취소
        Board board = boardMapper.find(applicationMapper.findById(id).getBoardId());
        if(board.getState() == 'S'){ // 대기 상태와 스터디 예약 완료되지 않은 경우만 삭제 (R) 처리
            return -1;
        }
        return applicationMapper.delete(id);
    }

    public int deleteByBoardId(int boardId){
        return applicationMapper.deleteByBoardId(boardId);
    }
}
