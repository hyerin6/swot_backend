package kr.devdogs.swot.application.service;

import kr.devdogs.swot.application.dto.Application;
import kr.devdogs.swot.application.mapper.ApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired ApplicationMapper applicationMapper;

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
        return applicationMapper.findByUserId(userId);
    }

    public int delete(int id){
        return applicationMapper.delete(id);
    }
}
